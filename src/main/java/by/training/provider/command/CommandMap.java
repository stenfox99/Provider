package by.training.provider.command;

import by.training.provider.command.impl.AddUser;
import by.training.provider.command.impl.SignIn;
import by.training.provider.command.impl.SignOut;
import by.training.provider.command.impl.ChangeUserPassword;

public enum CommandMap {
    SIGN_IN(new SignIn()), SIGN_OUT(new SignOut()), ADD_USER(new AddUser()), UPDATE_USER(new ChangeUserPassword());

    private CommandType commandType;

    CommandMap(CommandType commandType){
        this.commandType = commandType;
    }

    public CommandType getCommand(){
        return commandType;
    }
}
