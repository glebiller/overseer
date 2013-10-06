package fr.kissy.overseer.resource;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import fr.kissy.overseer.dto.EndpointDTO;
import fr.kissy.overseer.dto.JobKeyDTO;
import fr.kissy.overseer.service.EndpointService;
import org.apache.cxf.jaxrs.model.wadl.ElementClass;
import org.quartz.JobKey;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author Guillaume Le Biller (<i>lebiller@ekino.com</i>)
 * @version $Id$
 */
@Api("/endpoint")
@Path("/endpoint")
@Produces(MediaType.APPLICATION_JSON)
public class EndpointResource {
    @Autowired
    private EndpointService endpointService;

    @GET
    @ApiOperation(value = "List all endpoints")
    public List<EndpointDTO> list() {
        return endpointService.list();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Create an endpoint")
    public Response create(@ApiParam(required = true) final EndpointDTO endpointDTO) {
        EndpointDTO newEndpointDTO = endpointService.create(endpointDTO);
        return Response.status(Response.Status.CREATED).entity(newEndpointDTO).build();
    }

    @DELETE
    @Path("/{group}/{name}")
    @ApiOperation(value = "Delete an endpoint")
    public void delete(@ApiParam(required = true) @PathParam("group") final String group,
                       @ApiParam(required = true) @PathParam("name") final String name) {
        endpointService.delete(new JobKeyDTO(name, group));
    }
}
