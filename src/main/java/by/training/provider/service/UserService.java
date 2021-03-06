package by.training.provider.service;

import by.training.provider.dao.impl.TariffDaoImpl;
import by.training.provider.dao.impl.UserDaoImpl;
import by.training.provider.dao.impl.UserDataDaoImpl;
import by.training.provider.entity.Tariff;
import by.training.provider.entity.User;
import by.training.provider.entity.UserData;
import by.training.provider.exception.DaoException;
import by.training.provider.exception.LogicException;
import by.training.provider.util.Encrypt;
import by.training.provider.util.UserDataValidator;
import by.training.provider.util.UserValidator;

import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

/**
 * The Class UserService.
 */
public class UserService {
    
    /** The Constant DECREASE_TRAFFIC. */
    private static final int DECREASE_TRAFFIC = 10;

    /**
     * Find user.
     *
     * @param login the login
     * @param password the password
     * @return the optional
     * @throws LogicException the logic exception
     */
    public Optional<User> findUser(String login, String password) throws LogicException {
        Optional<User> user = Optional.empty();
        try {
            String encryptPassword = Encrypt.encrypt(password);
            List<User> users = UserDaoImpl.getInstance().findUserByLoginAndPassword(login, encryptPassword);
            if (!users.isEmpty()) {
                user = Optional.of(users.get(0));
            }
        } catch (DaoException e) {
            throw new LogicException(e);
        }
        return user;
    }

    /**
     * Update profile data.
     *
     * @param data the data
     * @throws LogicException the logic exception
     */
    public void updateProfileData(UserData data) throws LogicException {
        if (!UserDataValidator.validInitial(data.getFirstName()) || !UserDataValidator.validInitial(data.getLastName()) ||
                !UserDataValidator.validInitial(data.getPatronymic()) || !UserDataValidator.validEmail(data.getEmail()) ||
                !UserDataValidator.validPhone(data.getPhone())) {
            throw new LogicException("Incorrect input data");
        }
        try {
            UserDataDaoImpl.getInstance().update(data);
        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }

    /**
     * Change password.
     *
     * @param userId the user id
     * @param password the password
     * @param password2 the password 2
     * @throws LogicException the logic exception
     */
    public void changePassword(int userId, String password, String password2) throws LogicException {
        if (!UserValidator.validPassword(password) || !UserValidator.validPassword(password2)
                || !UserValidator.verifyPassword(password, password2)) {
            throw new LogicException("Incorrect input data");
        }
        try {
            password = Encrypt.encrypt(password);
            UserDaoImpl.getInstance().changePassword(userId, password);
        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }

    /**
     * Increase balance.
     *
     * @param userId the user id
     * @param currentBalance the current balance
     * @param balance the balance
     * @throws LogicException the logic exception
     */
    public void increaseBalance(int userId, BigDecimal currentBalance, BigDecimal balance) throws LogicException {
        if (balance.compareTo(BigDecimal.ZERO) < 0) {
            throw new LogicException("Incorrect input data");
        }
        BigDecimal updatedBalance = currentBalance.add(balance);
        try {
            UserDataDaoImpl.getInstance().updateBalance(userId, updatedBalance);
        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }

    /**
     * Find user data.
     *
     * @param userId the user id
     * @return the user data
     * @throws LogicException the logic exception
     */
    public UserData findUserData(int userId) throws LogicException {
        UserData userData;
        try {
            List<UserData> userDataList = UserDataDaoImpl.getInstance().findUserDataByUserId(userId);
            userData = userDataList.get(0);
        } catch (DaoException e) {
            throw new LogicException(e);
        }
        return userData;
    }

    /**
     * Change tariff.
     *
     * @param userId the user id
     * @param tariffName the tariff name
     * @throws LogicException the logic exception
     */
    public void changeTariff(int userId, String tariffName) throws LogicException {
        try {
            List<Tariff> tariff = TariffDaoImpl.getInstance().findByName(tariffName);
            UserDataDaoImpl.getInstance().changeTariff(tariff.get(0).getTariffId(), userId);
        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }

    /**
     * Upload image.
     *
     * @param userId the user id
     * @param imagePart the image part
     * @return the string
     * @throws LogicException the logic exception
     */
    public String uploadImage(int userId, Part imagePart) throws LogicException {
        try {
            InputStream inputStream = imagePart.getInputStream();
            UserDataDaoImpl.getInstance().updateImage(userId, inputStream);
            int fileSize = (int) imagePart.getSize();
            byte[] image = new byte[fileSize];
            imagePart.getInputStream().read(image, 0, fileSize);
            return Base64.getEncoder().encodeToString(image);
        } catch (DaoException | IOException e) {
            throw new LogicException(e);
        }
    }

    /**
     * Decrease traffic.
     *
     * @param userId the user id
     * @throws LogicException the logic exception
     */
    public void decreaseTraffic(int userId) throws LogicException {
        try {
            List<UserData> userData = UserDataDaoImpl.getInstance().findUserDataByUserId(userId);
            UserData user = userData.get(0);
            if (user.getTraffic() - DECREASE_TRAFFIC < 0){
                user.setTraffic(0);
            }else {
                user.setTraffic(user.getTraffic() - DECREASE_TRAFFIC);
            }
            UserDataDaoImpl.getInstance().updateTraffic(user);
        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }
}
