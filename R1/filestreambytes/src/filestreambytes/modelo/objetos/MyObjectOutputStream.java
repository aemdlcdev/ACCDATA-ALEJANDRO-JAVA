/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package filestreambytes.modelo.objetos;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 *
 * @author AEM by Alejandro Esteban Martinez de la Casa
 * @version 1.0
 * Created on 4 oct 2024
 *
 */
public class MyObjectOutputStream extends ObjectOutputStream{
        
    public MyObjectOutputStream (OutputStream out) throws IOException {
        super(out);
    }
    
    protected MyObjectOutputStream() throws IOException{
        super();
    }    
    
    @Override
    protected void writeStreamHeader() throws IOException{
        reset();
    }
}
