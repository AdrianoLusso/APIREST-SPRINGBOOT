package com.example.demo;

import com.example.demo.Service.InscripcionService;
import com.example.demo.domain.Curso;
import com.example.demo.domain.Estado;
import com.example.demo.domain.Estudiante;
import com.example.demo.domain.Inscripcion;
import com.example.demo.repository.CursoRepository;
import com.example.demo.repository.EstudianteRepository;
import com.example.demo.repository.InscripcionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.List;

//http://localhost:8080/swagger-ui/index.html?.configUrl=/v3/api-docs/swagger-config#/
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Autowired
	EstudianteRepository estudianteRepository;
	@Autowired
	CursoRepository cursoRepository;
	@Autowired
	InscripcionRepository inscripcionRepository;

	@Autowired
	InscripcionService inscripcionService;

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx){
		return args -> {
			System.out.println("TEST:");
			System.out.println("-------------------------------------- \n \n");

			//MOSTRAR LISTAS VACIAS
			System.out.println("ESTUDIANTES: "+estudianteRepository.findAll()+'\n');
			System.out.println("CURSOS: "+cursoRepository.findAll()+'\n');

			//CARGAR SEED
			seedInscripciones(seedCursos(),seedEstudiantes());

			//TP3:CONSULTAS
			System.out.println("CURSOS: "+cursoRepository.findAll()+'\n');
			System.out.println("ESTUDIANTES: "+estudianteRepository.findAll()+'\n');
			System.out.println("ESTS DNI MAYOR 20M Y APELLIDO ROMERO: " +
					estudianteRepository.findByApellidoAndDniGreaterThan("Romero",20000000)+'\n');
			System.out.println("INSCRP RECH Y PEND: " +
						inscripcionRepository.findByEstadoIn(List.of(Estado.PENDIENTE,Estado.RECHAZADA))+'\n');
			System.out.println("CURSOS EMPZ DESPUES DE 1/2/2020: " +
					cursoRepository.findByFechaDeInicioAfter(LocalDate.of(2020,2,1))+'\n');
			System.out.println("INSCRP RECH: " +
					inscripcionRepository.findByEstado(Estado.RECHAZADA)+'\n');
			System.out.println("INSCRP ACEPT: " +
					inscripcionRepository.findByEstadoNative("ACEPTADA")+'\n');
			System.out.println("ESTS DNI ASC, PAG 1 TAM 5: " +
					estudianteRepository.findAllByOrderByDniAsc(PageRequest.of(1,5)).toList()+'\n');
			System.out.println("ESTS DNI ASC, PAG 0 TAM 2: " +
					estudianteRepository.findAllByOrderByDniAsc(PageRequest.of(0,2)).toList()+'\n');

			//TP3:SERVICIO

			inscripcionService.createInscripcion(1L,3L);

		};
	}
	private List seedEstudiantes(){
		//Estudiantes
		Estudiante e1 = new Estudiante(
				43829385,
				"lussoadriano@gmail.com",
				LocalDate.of(2001,12,6),
				"Lusso",
				"Adriano"
		);

		Estudiante e2 = new Estudiante(
				30495234,
				"peperomero@gmail.com",
				LocalDate.of(1990,2,25),
				"Romero",
				"Pepe"
		);

		Estudiante e3 = new Estudiante(
				15952344,
				"lauraromero@gmail.com",
				LocalDate.of(1970,6,13),
				"Romero",
				"Laura"
		);
		Estudiante e4 = new Estudiante(
				15952344,
				"ramiro@gmail.com",
				LocalDate.of(2002,10,1),
				"Lusso",
				"Ramiro"
		);
		List<Estudiante> eList = List.of(e1,e2,e3,e4);

		estudianteRepository.saveAll(eList);

		return eList;
	}

	private List seedCursos(){

		//Cursos
		Curso c1 = new Curso(
				"muchas matematicas",
				LocalDate.of(2022,2,3),
				LocalDate.of(2022,6,5),
				"Algebra lineal");

		Curso c2 = new Curso(
				"Aprender a poner acentos",
				LocalDate.of(2010,5,3),
				LocalDate.of(2010,8,5),
				"Algebra lineal");

		Curso c3 = new Curso(
				"campana de gauss xd",
				LocalDate.of(2020,1,27),
				LocalDate.of(2022,3,5),
				"Probabilidad y Estadistica");

		Curso c4 = new Curso(
				"Linux y Xinu",
				LocalDate.of(2023,3,20),
				LocalDate.of(2023,12,12),
				"Sistemas Operativos");

		List<Curso> cList = List.of(c1,c2,c3,c4);
		cursoRepository.saveAll(cList);

		return cList;
	}

	private void seedInscripciones(List<Curso> cursos, List<Estudiante> estudiantes)
	{
		int i = 0;
		//Inscripciones

		Inscripcion i1 = new Inscripcion(
				Estado.ACEPTADA,
				LocalDate.of(2003,3,12),
				cursos.get(i),
				estudiantes.get(i));
		i++;

		Inscripcion i2 = new Inscripcion(
				Estado.RECHAZADA,
				LocalDate.of(2009,11,11),
				cursos.get(i),
				estudiantes.get(i));
		i++;

		Inscripcion i3 = new Inscripcion(
				Estado.ACEPTADA,
				LocalDate.of(1987,7,17),
				cursos.get(i),
				estudiantes.get(i));
		i++;

		Inscripcion i4 = new Inscripcion(
				Estado.PENDIENTE,
				LocalDate.of(2022,3,12),
				cursos.get(i),
				estudiantes.get(i));
		i++;

		List<Inscripcion> iList = List.of(i1,i2,i3,i4);
		inscripcionRepository.saveAll(iList);
	}
}
