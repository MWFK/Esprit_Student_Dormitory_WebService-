package tn.esprit.WebService;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import tn.esprit.business.RendezVousBusiness;
import tn.esprit.entites.RendezVous;

@Path("rendezvous")

public class RdvRessource {

public static RendezVousBusiness revMetier=new RendezVousBusiness();
@POST
@Consumes(MediaType.APPLICATION_JSON)
	public Response addRendezvous(RendezVous rdv)
	{
		if(rdv!=null) { revMetier.addRendezVous(rdv);
		return Response.status(Status.CREATED).entity("RDV ajouté avec succes").build();}
		return Response.status(Status.NOT_FOUND).entity("RDV not found").build();
	}


//ona commenté parceque elle fait la confusion entre les deux
/*@GET
@Produces(MediaType.APPLICATION_JSON)
   public Response getListRdv()
   {
	return Response.status(Status.OK).entity(revMetier.getListeRendezVous()).build();
	   
   }*/


@GET
@Produces(MediaType.APPLICATION_JSON)
public Response getListRdvByref(@QueryParam(value="refLogement") Integer refLogement)
   {if(refLogement!=null) 
	
	{List<RendezVous> rdvbyref=revMetier.getListeRendezVousByLogementReference(refLogement);
	if(rdvbyref != null)
	return Response.status(Status.OK).entity(revMetier.getListeRendezVousByLogementReference(refLogement)).build();
	else
		return Response.status(Status.NOT_FOUND).entity("List is empty").build();
	}
   return Response.status(Status.OK).entity(revMetier.getListeRendezVous()).build(); 
}


@GET
@Produces(MediaType.APPLICATION_JSON)
@Path("/{id}")	
   public Response getListRdvByid(@PathParam(value="id")Integer id)
   {if(id!=null) 
	
	{RendezVous rdvbyid= revMetier.getRendezVousById(id);
	if(rdvbyid != null)
	return Response.status(Status.OK).entity(revMetier.getRendezVousById(id)).build();
	else
		return Response.status(Status.NOT_FOUND).entity(" not found").build();
	}
   return Response.status(Status.OK).entity(revMetier.getRendezVousById(id)).build(); 
   }

@DELETE
@Path("/{id}")	
public Response DeleteRdvById(@PathParam(value="id")int id)
{
	if(revMetier.getRendezVousById(id)!=null) {
	
		revMetier.deleteRendezVous(id);
		return Response.status(Status.CREATED).entity("RDV supprimer avec succes").build();
	
	}
	return Response.status(Status.NOT_FOUND).entity("id not found").build();
	}
@PUT
@Path("/{id}")	
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public Response UpdateRdvById(@PathParam(value="id")int id,RendezVous rdv)
{
	if(revMetier.getRendezVousById(id)!=null) {
	
		revMetier.updateRendezVous(id, rdv);
		return Response.status(Status.CREATED).entity(revMetier.getRendezVousById(id)).build();
	
	}
	return Response.status(Status.NOT_FOUND).entity("id not found").build();
	}
}