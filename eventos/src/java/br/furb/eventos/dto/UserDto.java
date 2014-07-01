package br.furb.eventos.dto;

import net.sf.oval.constraint.Email;
import net.sf.oval.constraint.MinLength;
import net.sf.oval.constraint.NotEmpty;
import net.sf.oval.constraint.NotNull;

/**
 * @author Alexandre
 */
public class UserDto extends BaseDto {
    
    private String fullName;
    
    @NotNull(message="Campo Nome não pode ser vazio.")
    @NotEmpty(message="Campo Nome não pode ser vazio.")
    @MinLength(3)
    private String name;
    
    @NotNull(message="Campo Sobrenome não pode ser vazio.")
    @NotEmpty(message="Campo Sobrenome não pode ser vazio.")
    @MinLength(3)
    private String surName;
    
    private String photoUrl;

    @NotNull(message="Campo Email não pode ser vazio.")
    @NotEmpty(message="Campo Email não pode ser vazio.")
    @Email(message="Campo Email inválido.")
    private String login;
   
    // TODO: fazer um regex pra validar força da senha.
    @NotNull(message="Campo Senha não pode ser vazio.")
    @NotEmpty(message="Campo Senha não pode ser vazio.")
    private String pwd;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    
}
