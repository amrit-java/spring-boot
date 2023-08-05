package com.example.emp.Service;
 
import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.emp.domain.Student;
import com.example.emp.repository.StudentRepository;
 
@Service
public class StudentService {
@Autowired
    private StudentRepository repo;
public List<Student> listAll() {
        return repo.findAll();
    }
    
    public void save(Student std) {
    	try {
			JAXBContext jAXBContext= JAXBContext.newInstance(Student.class);
			Marshaller  marshaller= jAXBContext.createMarshaller();
			marshaller.setProperty(marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(std, System.out);;
			File file1 = new File("src\\main\\resources\\student.xml");
			marshaller.marshal(std, file1);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        repo.save(std);
    }
    
    public Student get(long id) {
        return repo.findById(id).get();
    }
    
    public void delete(long id) {
        repo.deleteById(id);
    }
 
}