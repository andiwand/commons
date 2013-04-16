package at.andiwand.commons.cli;

import java.io.IOException;

public interface CommandLineExecutor {

    public CommandLineInterface execute(String command) throws IOException;

}