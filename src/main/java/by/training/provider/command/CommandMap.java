package by.training.provider.command;

import by.training.provider.command.impl.*;
import by.training.provider.command.impl.admin.*;
import by.training.provider.command.impl.discount.AddDiscountCommand;
import by.training.provider.command.impl.discount.PrintDiscountCommand;
import by.training.provider.command.impl.discount.RemoveDiscountCommand;
import by.training.provider.command.impl.discount.UpdateDiscountCommand;
import by.training.provider.command.impl.tariff.AddTariffCommand;
import by.training.provider.command.impl.tariff.PrintTariffCommand;
import by.training.provider.command.impl.tariff.RemoveTariffCommand;
import by.training.provider.command.impl.tariff.UpdateTariffCommand;
import by.training.provider.command.impl.user.*;

public enum CommandMap {
    SIGN_IN(new SignInCommand()), SIGN_OUT(new SignOutCommand()), ADD_USER(new AddUserCommand()), ADD_TARIFF(new AddTariffCommand()), REMOVE_TARIFF(new RemoveTariffCommand()),
    UPDATE_TARIFF(new UpdateTariffCommand()), TO_MAIN_PAGE(new ToMainPageCommand()), PRINT_TARIFFS(new PrintTariffCommand()), PRINT_DISCOUNTS(new PrintDiscountCommand()),
    ADD_DISCOUNT(new AddDiscountCommand()), REMOVE_DISCOUNT(new RemoveDiscountCommand()), UPDATE_DISCOUNT(new UpdateDiscountCommand()), LANGUAGE(new ChangeLocaleCommand()),
    TO_PROFILE(new ToProfileCommand()), CHANGE_PROFILE_INFO(new ChangeProfileInfoCommand()), CHANGE_PASSWORD(new ChangePasswordCommand()), INCREASE_BALANCE(new IncreaseBalanceCommand()),
    PRINT_USER(new PrintUserCommand()), UNBAN_USER(new UnbanUserCommand()), BAN_USER(new BanUserCommand()), CONNECT_TO_TARIFF(new ConnectToTariffCommand()), UPLOAD_IMAGE(new UploadImage()),
    FILL_TRAFFIC(new FillTrafficCommand());

    private Command commandType;

    CommandMap(Command commandType) {
        this.commandType = commandType;
    }

    public Command getCommand() {
        return commandType;
    }
}
