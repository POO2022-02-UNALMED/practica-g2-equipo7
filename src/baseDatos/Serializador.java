package baseDatos;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import gestorAplicacion.gestion.*;
import gestorAplicacion.personas.*;
//;
//out.writeObject(Facultad.getListaFacultades());
//out.writeObject(Estudiante.getListaEstudiantes());
//out.writeObject(Profesor.getListaProfesores());

public class Serializador implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void SerializarAsignaturas() {

		// Serializar
		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream(System.getProperty("user.dir") + "\\src\\baseDatos\\tmp\\Asignaturas.txt");

			ObjectOutputStream out = new ObjectOutputStream(fileOut);

			out.writeObject(Asignatura.getListaAsignaturas());
			out.close();
			fileOut.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void DeserializarAsignaturas() {

		FileInputStream fileIn;
		try {
			fileIn = new FileInputStream(System.getProperty("user.dir") + "/src/baseDatos/tmp/Asignaturas.txt");

			ObjectInputStream in = new ObjectInputStream(fileIn);

			ArrayList<Asignatura> listado = (ArrayList<Asignatura>) in.readObject();
			Asignatura.setListaAsignaturas(listado);

			in.close();
			fileIn.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void SerializarBecas() {

		// Serializar
		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream(System.getProperty("user.dir") + "\\src\\baseDatos\\tmp\\Becas.txt");

			ObjectOutputStream out = new ObjectOutputStream(fileOut);

			out.writeObject(Beca.getListaBecas());
			out.close();
			fileOut.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void DeserializarBecas() {

		FileInputStream fileIn;
		try {
			fileIn = new FileInputStream(System.getProperty("user.dir") + "/src/baseDatos/tmp/Becas.txt");

			ObjectInputStream in = new ObjectInputStream(fileIn);

			ArrayList<Beca> listado = (ArrayList<Beca>) in.readObject();
			Beca.setListaBecas(listado);
			in.close();
			fileIn.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void SerializarFacultades() {

		// Serializar
		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream(System.getProperty("user.dir") + "\\src\\baseDatos\\tmp\\Facultades.txt");

			ObjectOutputStream out = new ObjectOutputStream(fileOut);

			out.writeObject(Facultad.getListaFacultades());
			out.close();
			fileOut.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void DeserializarFacultades() {

		FileInputStream fileIn;
		try {
			fileIn = new FileInputStream(System.getProperty("user.dir") + "/src/baseDatos/tmp/Facultades.txt");

			ObjectInputStream in = new ObjectInputStream(fileIn);

			ArrayList<Facultad> listado = (ArrayList<Facultad>) in.readObject();
			Facultad.setListaFacultades(listado);
			in.close();
			fileIn.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void SerializarEstudiantes() {

		// Serializar
		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream(System.getProperty("user.dir") + "\\src\\baseDatos\\tmp\\Estudiantes.txt");

			ObjectOutputStream out = new ObjectOutputStream(fileOut);

			out.writeObject(Estudiante.getListaEstudiantes());
		
			out.close();
			fileOut.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void DeserializarEstudiantes() {

		FileInputStream fileIn;
		try {
			fileIn = new FileInputStream(System.getProperty("user.dir") + "/src/baseDatos/tmp/Estudiantes.txt");

			ObjectInputStream in = new ObjectInputStream(fileIn);

			ArrayList<Estudiante> listado = (ArrayList<Estudiante>) in.readObject();
			Estudiante.setListaEstudiantes(listado);
			in.close();
			fileIn.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void SerializarProfesores() {

		// Serializar
		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream(System.getProperty("user.dir") + "\\src\\baseDatos\\tmp\\Profesores.txt");

			ObjectOutputStream out = new ObjectOutputStream(fileOut);

			out.writeObject(Profesor.getListaProfesores());
			out.close();
			fileOut.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void DeserializarProfesores() {

		FileInputStream fileIn;
		try {
			fileIn = new FileInputStream(System.getProperty("user.dir") + "/src/baseDatos/tmp/Profesores.txt");

			ObjectInputStream in = new ObjectInputStream(fileIn);

			ArrayList<Profesor> listado = (ArrayList<Profesor>) in.readObject();
			Profesor.setListaProfesores(listado);
			in.close();
			fileIn.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



public static void SerializarSubsidios() {

	// Serializar
	FileOutputStream fileOut;
	try {
		fileOut = new FileOutputStream(System.getProperty("user.dir") + "\\src\\baseDatos\\tmp\\Subsidios.txt");

		ObjectOutputStream out = new ObjectOutputStream(fileOut);

		out.writeObject(Subsidio.getListaSubsidio());
		out.close();
		fileOut.close();

	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public static void DeserializarSubsidios() {

	FileInputStream fileIn;
	try {
		fileIn = new FileInputStream(System.getProperty("user.dir") + "/src/baseDatos/tmp/Subsidios.txt");

		ObjectInputStream in = new ObjectInputStream(fileIn);

		ArrayList<Subsidio> listado = (ArrayList<Subsidio>) in.readObject();
		Subsidio.setListaSubsidio(listado);
		in.close();
		fileIn.close();

	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

}