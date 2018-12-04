package by.training.provider.command;

import by.training.provider.command.impl.*;

public enum CommandMap {
    SIGN_IN(new SignIn()), SIGN_OUT(new SignOut()), ADD_USER(new AddUser()), UPDATE_USER(new ChangeUserPassword()),
    ADD_TARIFF(new AddTariff()), REMOVE_TARIFF(new RemoveTariff()), UPDATE_TARIFF(new UpdateTariff());

    private Command commandType;

    CommandMap(Command commandType){
        this.commandType = commandType;
    }

    public Command getCommand(){
        return commandType;
    }
}
