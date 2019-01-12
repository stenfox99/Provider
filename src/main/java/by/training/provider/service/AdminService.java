package by.training.provider.service;

import by.training.provider.dao.impl.*;
import by.training.provider.entity.*;
import by.training.provider.exception.DaoException;
import by.training.provider.exception.LogicException;
import by.training.provider.util.DiscountValidator;
import by.training.provider.util.Encrypt;
import by.training.provider.util.TariffValidator;
import by.training.provider.util.UserValidator;

import java.util.List;

/**
 * The Class AdminService.
 */
public class AdminService {

    /**
     * Adds the user.
     *
     * @param user the user
     * @throws LogicException the logic exception
     */
    public void addUser(User user) throws LogicException {
        if (!UserValidator.validLogin(user.getLogin()) || !UserValidator.validPassword(user.getPassword())) {
            throw new LogicException("The incorrect input data");
        }
        try {
            List<User> users = UserDaoImpl.getInstance().findUserByLogin(user.getLogin());
            if (users.isEmpty()) {
                user.setPassword(Encrypt.encrypt(user.getPassword()));
                UserDaoImpl.getInstance().add(user);
                List<User> dbUser = UserDaoImpl.getInstance().findUserByLogin(user.getLogin());
                UserData userData = new UserData(dbUser.get(0).getUserId());
                UserDataDaoImpl.getInstance().add(userData);
            } else {
                throw new LogicException("This login already exists");
            }
        } catch (DaoException e) {
            throw new LogicException(e);
        }

    }

    /**
     * Adds the admin.
     *
     * @param user the user
     * @throws LogicException the logic exception
     */
    public void addAdmin(User user) throws LogicException {
        if (!UserValidator.validLogin(user.getLogin()) || !UserValidator.validPassword(user.getPassword())) {
            throw new LogicException("The incorrect input data");
        }
        try {
            List<User> users = UserDaoImpl.getInstance().findUserByLogin(user.getLogin());
            if (users.isEmpty()) {
                user.setPassword(Encrypt.encrypt(user.getPassword()));
                UserDaoImpl.getInstance().add(user);
            } else {
                throw new LogicException("This login already exists");
            }
        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }

    /**
     * Adds the tariff.
     *
     * @param tariff the tariff
     * @throws LogicException the logic exception
     */
    public void addTariff(Tariff tariff) throws LogicException {
        if (!TariffValidator.validTariffName(tariff.getName()) || !TariffValidator.validPrice(tariff.getPrice()) ||
                !TariffValidator.validDescription(tariff.getDescription()) || !TariffValidator.validTraffic(tariff.getMonthTraffic())) {
            throw new LogicException("Incorrect input data");
        }
        try {
            List<Tariff> existedTariff = TariffDaoImpl.getInstance().findByName(tariff.getName());
            if (existedTariff.isEmpty()) {
                TariffDaoImpl.getInstance().add(tariff);
            } else {
                throw new LogicException("This name already exists");
            }
        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }

    /**
     * Removes the tariff.
     *
     * @param tariffName the tariff name
     * @throws LogicException the logic exception
     */
    public void removeTariff(String tariffName) throws LogicException {
        Tariff tariff = new Tariff(tariffName);
        try {
            TariffDaoImpl.getInstance().remove(tariff);
        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }

    /**
     * Update tariff.
     *
     * @param tariff the tariff
     * @throws LogicException the logic exception
     */
    public void updateTariff(Tariff tariff) throws LogicException {
        if (!TariffValidator.validTariffName(tariff.getName()) || !TariffValidator.validPrice(tariff.getPrice()) ||
                !TariffValidator.validDescription(tariff.getDescription()) || !TariffValidator.validTraffic(tariff.getMonthTraffic())) {
            throw new LogicException("Incorrect input data");
        }
        try {
            TariffDaoImpl.getInstance().update(tariff);
        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }

    /**
     * Adds the discount.
     *
     * @param discount the discount
     * @throws LogicException the logic exception
     */
    public void addDiscount(Discount discount) throws LogicException {
        if (!DiscountValidator.validDiscountName(discount.getName()) || !DiscountValidator.validDescription(discount.getDescription())
                || !DiscountValidator.validDiscountValue(discount.getDiscountValue()) || discount.getBeginningDate().compareTo(discount.getEndDate()) > 0) {
            throw new LogicException("Incorrect input data");
        }
        try {
            List<Discount> existedDiscounts = DiscountDaoImpl.getInstance().findByName(discount.getName());
            if (existedDiscounts.isEmpty()) {
                List<Discount> discountsByTariff = DiscountDaoImpl.getInstance().findByTariffName(discount.getTariff().getName());
                if (discountsByTariff.isEmpty()) {
                    List<Tariff> tariff = TariffDaoImpl.getInstance().findByName(discount.getTariff().getName());
                    if (tariff.isEmpty()) {
                        throw new LogicException("Selected tariff doesn't exist");
                    }
                    discount.getTariff().setTariffId(tariff.get(0).getTariffId());
                    DiscountDaoImpl.getInstance().add(discount);
                } else {
                    throw new LogicException("This tariff already has discount");
                }
            } else {
                throw new LogicException("This name already exists");
            }
        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }

    /**
     * Removes the discount.
     *
     * @param discount the discount
     * @throws LogicException the logic exception
     */
    public void removeDiscount(Discount discount) throws LogicException {
        try {
            DiscountDaoImpl.getInstance().remove(discount);
        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }

    /**
     * Update discount.
     *
     * @param discount the discount
     * @throws LogicException the logic exception
     */
    public void updateDiscount(Discount discount) throws LogicException {
        if (!DiscountValidator.validDiscountName(discount.getName()) || !DiscountValidator.validDescription(discount.getDescription())
                || !DiscountValidator.validDiscountValue(discount.getDiscountValue()) || discount.getBeginningDate().compareTo(discount.getEndDate()) > 0) {
            throw new LogicException("Incorrect input data");
        }
        try {
            List<Tariff> tariff = TariffDaoImpl.getInstance().findByName(discount.getTariff().getName());
            if (tariff.isEmpty()) {
                throw new DaoException("Selected tariff doesn't exist");
            }
            discount.getTariff().setTariffId(tariff.get(0).getTariffId());
            DiscountDaoImpl.getInstance().update(discount);
        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }

    /**
     * Find all user.
     *
     * @return the list
     * @throws LogicException the logic exception
     */
    public List<User> findAllUser() throws LogicException {
        List<User> users;
        try {
            users = UserDaoImpl.getInstance().findAll();
        } catch (DaoException e) {
            throw new LogicException(e);
        }
        return users;
    }

    /**
     * Find all user type.
     *
     * @return the list
     * @throws LogicException the logic exception
     */
    public List<UserType> findAllUserType() throws LogicException {
        List<UserType> users;
        try {
            users = UserTypeDaoImpl.getInstance().findAll();
        } catch (DaoException e) {
            throw new LogicException(e);
        }
        return users;
    }

    /**
     * Ban user.
     *
     * @param login the login
     * @throws LogicException the logic exception
     */
    public void banUser(String login) throws LogicException {
        try {
            UserDaoImpl.getInstance().banUser(login);
        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }

    /**
     * Unban user.
     *
     * @param login the login
     * @throws LogicException the logic exception
     */
    public void unbanUser(String login) throws LogicException {
        try {
            UserDaoImpl.getInstance().unbanUser(login);
        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }

    /**
     * Fill traffic.
     *
     * @throws LogicException the logic exception
     */
    public void fillTraffic() throws LogicException {
        try {
            List<UserData> allData = UserDataDaoImpl.getInstance().findAll();
            for (UserData data : allData) {
                if (data.getTariff().getPriceWithDiscount() != null &&
                        data.getBalance().compareTo(data.getTariff().getPriceWithDiscount()) >= 0) {
                        data.setBalance(data.getBalance().subtract(data.getTariff().getPriceWithDiscount()));
                        data.setTraffic(data.getTraffic() + data.getTariff().getMonthTraffic());
                        UserDataDaoImpl.getInstance().updateBalanceAndTraffic(data);
                }
            }
        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }
}
