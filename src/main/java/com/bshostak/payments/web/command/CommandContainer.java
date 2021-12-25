package com.bshostak.payments.web.command;

import java.util.Map;
import java.util.TreeMap;

/**
 * Holder for all commands.<br/>
 *
 * @author B.Shostak
 *
 */

public class CommandContainer {

    //private static final Logger log = Logger.getLogger(CommandContainer.class); do it later!!!;

    private static Map<String, Command> commands = new TreeMap<String, Command>();
    static {

        // common commands
        commands.put("login", new LoginCommand());
        commands.put("signUp", new SignUpCommand());
        commands.put("goToLoginPage", new GoToLoginCommand());
        commands.put("goToSignupPage", new GoToSignUpCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("switch_language", new SwitchLanguageCommand());
        //commands.put("noCommand", new NoCommand());
        //commands.put("viewSettings", new ViewSettingsCommand());
        //commands.put("updateSettings", new UpdateSettingsCommand());

        // client commands
        commands.put("listMenu", new ListMenuCommand());
        commands.put("userMain", new UserMainCommand());
        commands.put("adminMain", new AdminMainCommand());
        commands.put("goToTopUpBalance", new GoToTopUpBalanceCommand());
        commands.put("goToNewPayment", new GoToNewPaymentCommand());
        commands.put("topUpBalance", new TopUpBalanceCommand());
        commands.put("newPayment", new NewPaymentCommand());
        commands.put("payments", new PaymentsCommand());
        commands.put("accounts", new AccountsCommand());
        commands.put("goToProfile", new GoToProfileCommand());

        // admin commands
        //commands.put("listOrders", new ListOrdersCommand());

        //log.debug("Command container was successfully initialized");
        //log.trace("Number of commands --> " + commands.size());
    }

    /**
     * Returns command object with the given name.
     *
     * @param commandName
     *            Name of the command.
     * @return Command object.
     */
    public static Command get(String commandName) {
        if (commandName == null || !commands.containsKey(commandName)) {
            return commands.get("noCommand");
        }
        return commands.get(commandName);
    }

}
