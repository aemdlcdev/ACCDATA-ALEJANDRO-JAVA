/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.aem.ejercicio3examen.modelo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

/**
 * Clase para gestionar el contenido de un documento XML utilizando DOM.
 * 
 * @version 1.0
 * Creado el 16 oct 2024
 * 
 */
public class GestionContenidoDom {
    
        Document documento;
        DocumentBuilderFactory factory;
        DocumentBuilder builder;

        /**
         * Constructor que inicializa el documento XML con un nodo principal.
         * 
         * @param nombre Nombre del nodo principal
         */
        public GestionContenidoDom(String nombre){
            try {
                factory = DocumentBuilderFactory.newInstance();
                builder = factory.newDocumentBuilder();
                DOMImplementation implementation = builder.getDOMImplementation();
                this.documento = (Document) implementation.createDocument(null, nombre, null);
                this.documento.setXmlVersion("1.0");
            } catch (ParserConfigurationException ex) {
                Logger.getLogger(GestionContenidoDom.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        /**
         * Añade un nodo al documento.
         * 
         * @param nombreNodo Nombre del nodo
         * @return nodoPrincipal El nodo creado
         */
        public Element addNodo(String nombreNodo){
            Element nodoPrincipal = this.documento.createElement(nombreNodo);
            this.documento.getDocumentElement().appendChild(nodoPrincipal);
            return nodoPrincipal;
        }

        /**
         * Añade un nodo hijo a un nodo padre existente.
         * 
         * @param datoEmpleado Nombre del nodo hijo
         * @param elementoPadre Nodo padre
         * @return El nodo hijo creado
         */
        public Element addNodo(String datoEmpleado, Element elementoPadre){
            Element dato = this.documento.createElement(datoEmpleado);
            elementoPadre.appendChild(dato);
            return dato;
        }

        /**
         * Crea un transformer y le da formato.
         * 
         * @param indent Indentación para el formato del XML yes/no
         * @return transformer con formato
         */
        private Transformer preProcess(String indent){
            Transformer transformer = null;
            try {
                transformer = TransformerFactory.newInstance().newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT , indent); // Para que salga el sangrado y no todo en una línea
                transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
                return transformer;
            } catch (TransformerConfigurationException ex) {
                Logger.getLogger(GestionContenidoDom.class.getName()).log(Level.SEVERE, null, ex);
            }
            return transformer;
        }

        /**
         * Muestra el contenido del documento XML en la consola.
         * 
         * @param indent Indentación para el formato del XML
         */
        public void mostrarPantalla(String indent){
            try {
                Source source = new DOMSource(this.documento);
                Result salida = new StreamResult(System.out);
                preProcess(indent).transform(source, salida);
            } catch (TransformerException ex) {
                Logger.getLogger(GestionContenidoDom.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        /**
         * Carga un archivo XML en memoria.
         * 
         * @param nombreArchivo Nombre del archivo a cargar
         */
        public void cargarArchivoEnMemoria(String nombreArchivo){
            try {
                this.documento = this.builder.parse(new File(nombreArchivo));
                this.documento.getDocumentElement().normalize();
            } catch (SAXException | IOException ex) {
                Logger.getLogger(GestionContenidoDom.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        /**
         * Obtiene el nombre del elemento principal del documento.
         * 
         * @return Nombre del elemento principal
         */
        public String getElementPrincipal(){
            return this.documento.getDocumentElement().getNodeName();
        }

        /**
         * Obtiene el valor de un tag específico dentro de un elemento.
         * 
         * @param tag Nombre del tag
         * @param elemento Elemento que contiene el tag
         * @return Valor del tag
         */
        public String getTagValue(String tag, Element elemento) {
            NodeList nodeList = elemento.getElementsByTagName(tag);
            if (nodeList != null && nodeList.getLength() > 0) { // Compruebo que la lista no sea null y no esté vacía
                NodeList childNodes = nodeList.item(0).getChildNodes();
                if (childNodes != null && childNodes.getLength() > 0) {
                    Node node = childNodes.item(0);
                    if (node != null) {
                        return node.getNodeValue();
                    }
                }
            }
            return null;
        }
        
        public void addNodoYTexto(String nombreNodo, String texto, Element raiz) {
            Element nodo = this.documento.createElement(nombreNodo);
            Text textoNodo = this.documento.createTextNode(texto);
            nodo.appendChild(textoNodo);
            raiz.appendChild(nodo);
        }
        
        public void generarArchivoDelDOM(String nombreArchivo, String indent){
            try {
                Source source = new DOMSource(this.documento);
                Result salida = new StreamResult(new File(nombreArchivo));
                preProcess(indent).transform(source, salida);
            } catch (TransformerException ex) {
                Logger.getLogger(GestionContenidoDom.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        /**
         * Elimina un elemento del documento XML.
         * 
         * @param nombreElemento Elemento de empleado a eliminar
         */
        public void eliminarElemento( String nombreElemento) {
            NodeList nodeList = this.documento.getElementsByTagName(nombreElemento);
            for (int i = 0; i < nodeList.getLength(); i++) {
                
                Element empleElemento = (Element) nodeList.item(i);
                
                NodeList elementos = empleElemento.getElementsByTagName(nombreElemento);
                
                if(elementos.getLength()>0){
                    Node elementoABorrar = elementos.item(0);
                    empleElemento.removeChild(elementoABorrar);
                }
            }
        }
        
        public void añadirProducto(String nombre, double cantidad, String supermercado){
            
            this.cargarArchivoEnMemoria("./resources/listaCompra.xml");
            
            NodeList nodeList = this.documento.getElementsByTagName("producto");
            boolean encontrar=true; // Para salirme del bucle cuando encuentre el empleado que quiero
            int i = 0;
            while(encontrar && i < nodeList.getLength()){

                Element prodcutoElement = (Element) nodeList.item(i);
                String idStr = getTagValue("nombre", prodcutoElement);

                if (!nombre.equals(idStr)) {
                    
                    Element productoElementNoE = addNodo("producto");
                    addNodoYTexto("nombre", nombre, productoElementNoE);
                    addNodoYTexto("cantidad", String.valueOf(cantidad), productoElementNoE);
                    addNodoYTexto("supermercado", supermercado, productoElementNoE);
                    encontrar = false;
                }
                
                else{
                    Element superElement = (Element) prodcutoElement.getElementsByTagName("supermercado").item(0);
                    superElement.setTextContent(supermercado);
                    
                    Element cantidadElement = (Element) prodcutoElement.getElementsByTagName("cantidad").item(0);
                    cantidadElement.setTextContent(Double.toString(cantidad));
                    encontrar=false;
                }
                i++;
            }
            
            this.generarArchivoDelDOM("./resources/listaCompra.xml", "yes");
            
            this.mostrarPantalla("yes");

        }
    
        
        
}
