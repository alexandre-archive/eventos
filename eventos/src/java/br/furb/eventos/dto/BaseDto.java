package br.furb.eventos.dto;

import net.sf.oval.constraint.Min;
import net.sf.oval.constraint.NotNull;

/**
 *
 * @author alexandre.vicenzi
 */
public class BaseDto {
    
    @NotNull
    @Min(0)
    protected long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
}
