package by.training.provider.command;

import by.training.provider.command.impl.*;

public enum CommandMap {
    SIGN_IN(new SignIn()), SIGN_OUT(new SignOut()), ADD_USER(new AddUser()), UPDATE_USER(new ChangeUserPassword()),
    ADD_TARIFF(new AddTariff());

    private CommandType commandType;

    CommandMap(CommandType commandType){
        this.commandType = commandType;
    }

    public CommandType getCommand(){
        return commandType;
    }
}
