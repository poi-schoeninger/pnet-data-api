package pnet.data.api.todo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * One referenced person of a to-do group.
 *
 * @author HAM
 */
@ApiModel(description = "One referenced person of a to-do group.")
public class TodoGroupPersonLinkDTO implements Serializable
{

    private static final long serialVersionUID = -4823204025294192488L;

    @ApiModelProperty(notes = "The id of the person.")
    private final Integer personId;

    @ApiModelProperty(notes = "The name of the person.")
    private final String name;

    @ApiModelProperty(notes = "True, if the person has some open work to do in this group.")
    private final boolean obliged;

    public TodoGroupPersonLinkDTO(@JsonProperty("personId") Integer personId, @JsonProperty("name") String name,
        @JsonProperty("obliged") boolean obliged)
    {
        super();

        this.personId = personId;
        this.name = name;
        this.obliged = obliged;
    }

    public Integer getPersonId()
    {
        return personId;
    }

    public String getName()
    {
        return name;
    }

    public boolean isObliged()
    {
        return obliged;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + (obliged ? 1231 : 1237);
        result = prime * result + ((personId == null) ? 0 : personId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (!(obj instanceof TodoGroupPersonLinkDTO))
        {
            return false;
        }
        TodoGroupPersonLinkDTO other = (TodoGroupPersonLinkDTO) obj;
        if (name == null)
        {
            if (other.name != null)
            {
                return false;
            }
        }
        else if (!name.equals(other.name))
        {
            return false;
        }
        if (obliged != other.obliged)
        {
            return false;
        }
        if (personId == null)
        {
            if (other.personId != null)
            {
                return false;
            }
        }
        else if (!personId.equals(other.personId))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return String.format("TodoGroupPersonLinkDTO [personId=%s, name=%s, obliged=%s]", personId, name, obliged);
    }

}
