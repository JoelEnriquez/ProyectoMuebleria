/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloFinanciera;

import DBConnection.Conexion;
import EntidadesMuebleria.EnsamblePieza;
import EntidadesMuebleria.Mueble;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author joel
 */
public class ModeloConsultaMueble {

    private final String getMueblesQuery = "SELECT * FROM Mueble";
    private final String getRecetasMueble = "SELECT * FROM Ensamble_Pieza";
    private final Connection conexion = Conexion.getConexion();

    public ArrayList<Mueble> getListMuebles() {
        ArrayList<Mueble> listMuebles = new ArrayList<>();
        int numeroMueble = 0;
        try ( PreparedStatement ps = conexion.prepareStatement(getMueblesQuery); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                numeroMueble++;
                listMuebles.add(new Mueble(
                        numeroMueble,
                        rs.getString(1),
                        rs.getDouble(2)
                ));
            }

        } catch (Exception e) {
        }
        return listMuebles;
    }
    
    public ArrayList<EnsamblePieza> getListRecetaMueble() {
        ArrayList<EnsamblePieza> listRecetas = new ArrayList<>();
        int numeroEnsamble = 0;
        try ( PreparedStatement ps = conexion.prepareStatement(getRecetasMueble); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                numeroEnsamble++;
                listRecetas.add(new EnsamblePieza(
                        numeroEnsamble,
                        rs.getString(1),
                        rs.getString(2),
                        rs.getInt(3))
                );
            }
        } catch (Exception e) {
        }
        return listRecetas;
    }
}
