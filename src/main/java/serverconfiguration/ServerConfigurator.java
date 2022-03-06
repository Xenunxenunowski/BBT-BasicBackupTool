package serverconfiguration;

public class ServerConfigurator {
    private String serverDomain;
    private char[] serverPassword;
    private int portNumber;
    private boolean autoLogin,workAsClient;

    public ServerConfigurator(String serverDomain, char[] serverPassword, int portNumber, boolean autoLogin, boolean workAsClient) {
        this.serverDomain = serverDomain;
        this.serverPassword = serverPassword;
        this.portNumber = portNumber;
        this.autoLogin = autoLogin;
        this.workAsClient = workAsClient;
    }

    public ServerConfigurator() {
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
