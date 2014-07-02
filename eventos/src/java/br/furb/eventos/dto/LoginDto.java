package br.furb.eventos.dto;

import net.sf.oval.constraint.NotEmpty;
import net.sf.oval.constraint.NotNull;

/**
 *
 * @author alexandre.vicenzi
 */
public class LoginDto {

    @NotNull
    @NotEmpty
    private String login;

    @NotNull
    @NotEmpty
    private String pwd;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
