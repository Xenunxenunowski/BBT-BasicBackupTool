package serverconfiguration;

public class ServerConfigurator {
    private static String serverDomain;
    private static char[] serverPassword;
    private static int portNumber;
    private static boolean autoLogin,workAsClient;
    private static ServerConfigurator serverConfigurator = new ServerConfigurator();

    public static ServerConfigurator getInstance( ) {
        return serverConfigurator;
    }

    private ServerConfigurator() {
    }

    public boolean isWorkAsClient() {
        return workAsClient;
    }

    public void setWorkAsClient(boolean workAsClient) {
        this.workAsClient = workAsClient;
    }

    public String getServerDomain() {
        return serverDomain;
    }

    public void setServerDomain(String serverDomain) {
        this.serverDomain = serverDomain;
    }

    public char[] getServerPassword() {
        return serverPassword;
    }

    public void setServerPassword(char[] serverPassword) {
        this.serverPassword = serverPassword;
    }

    public int getPortNumber() {
        return portNumber;
    }

    public void setPortNumber(int portNumber) {
        this.portNumber = portNumber;
    }

    public boolean isAutoLogin() {
        return autoLogin;
    }

    public void setAutoLogin(boolean autoLogin) {
        this.autoLogin = autoLogin;
    }
}
