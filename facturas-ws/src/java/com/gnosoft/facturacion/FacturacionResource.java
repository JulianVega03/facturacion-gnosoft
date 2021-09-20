package com.gnosoft.facturacion;

import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fachada.FachadaFacturacion;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import model.FacturaVO;

@Path("factura")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FacturacionResource {

    private final FachadaFacturacion facturacion;

    public FacturacionResource() {
        facturacion = new FachadaFacturacion();
    }

    @GET
    public List<FacturaVO> listarFacturas() {
        List<FacturaVO> facturas = facturacion.ListarFacturas();
        return facturas;
    }

    @GET
    @Path("/{id}")
    public Response obtenerFactura(@PathParam("id") int id) {
        FacturaVO factura = facturacion.obtenerFactura(id);
        if (factura == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("la factura #" + id + " no existe").build();
        }
        return Response.ok(factura).build();
    }

    @POST
    public Response crearFactura(FacturaVO factura) {
        FacturaVO facturaR = facturacion.crearFactura(factura);
        if (facturaR == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("No fue posible crear la factura").build();
        }
        return Response.status(Response.Status.CREATED).build();
    }

    @DELETE
    @Path("/{id}")
    public Response eliminarFactura(@PathParam("id") int id) {
        boolean eliminado = facturacion.eliminarFactura(id);
        if (!eliminado) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Factura #" + id + " no encontrada").build();
        }
        return Response.ok().build();
    }

    @PUT
    public Response actualizarFactura(FacturaVO factura) {
        boolean actualizado = facturacion.actualizarFactura(factura);
        if(!actualizado) {
           return Response.ok("No fue posible actualizar la factura").build();
        }
        return Response.ok().build();
    }

}