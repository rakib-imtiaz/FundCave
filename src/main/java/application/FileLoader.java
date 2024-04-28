//package application;
//
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//public class FileLoader {
//
//	static String doctorfileName = "Doctor_list.txt";
//	static String patientfileName = "Patient_list.txt";
//	static String appointmentFileName = "Appointment_list.txt";
//
//	private static List<Patient> getSamplePatients() {
//		List<Patient> patients = new ArrayList<>();
//
//		patients.add(new Patient("1", "John Doe", 30, "Male", "+1234567890", "john@example.com", "pass123"));
//		patients.add(new Patient("2", "Jane Smith", 25, "Female", "+9876543210", "jane@example.com", "patient456"));
//		patients.add(new Patient("3", "Michael Johnson", 45, "Male", "+1112223333", "michael@example.com", "mike789"));
//		patients.add(new Patient("4", "Emily Brown", 28, "Female", "+5556667777", "emily@example.com", "emily123"));
//		// Add more patients as needed
//
//		return patients;
//	}
//
//	// Load patient data from a file
//	public static List<Patient> loadPatientsDataFromFile() {
//		List<Patient> patients = new ArrayList<>();
//
//		/// 1,Dr. Ahmed Khan,Cardiologist,MBBS,
//		/// MD,+8801XXXXXXXXX,dr.ahmed@example.com,password123
//
//		try (BufferedReader reader = new BufferedReader(new FileReader(patientfileName))) {
//			String line;
//			while ((line = reader.readLine()) != null) {
//
//				System.out.println(line);
//				String[] data = line.split(",");
//				System.out.println(data.length);
//
//				String id = data[0];
//				String name = data[1];
//				int age = Integer.parseInt(data[2]);
//				String gender = data[3];
//				String contactNumber = data[4];
//				String email = data[5];
//				String password = data[6];
//				patients.add(new Patient(id, name, age, gender, contactNumber, email, password));
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return patients;
//	}
//
//	// Save patient data to a file
//	public static void savePatientsDataToFile(List<Patient> patientList) {
//		try (BufferedWriter writer = new BufferedWriter(new FileWriter(patientfileName))) {
//			for (Patient patient : patientList) {
//				String patientData = String.format("%s,%s,%d,%s,%s,%s,%s\n", patient.getId(), patient.getName(),
//						patient.getAge(), patient.getGender(), patient.getContactNumber(), patient.getEmail(),
//						patient.getPassword());
//				writer.write(patientData);
//			}
//			System.out.println("Patient data saved to '" + patientfileName + "'");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	private static List<Doctor> getSampleDoctors() {
//		List<Doctor> doctors = new ArrayList<>();
//
//		doctors.add(new Doctor(1, "Dr. Ahmed Khan", "Cardiologist", "MBBS; MD", "+8801XXXXXXXXX",
//				"dr.ahmed@example.com", "password123"));
//		doctors.add(new Doctor(2, "Dr. Farah Islam", "Dermatologist", "MBBS; FCPS", "+8801XXXXXXXXX",
//				"dr.farah@example.com", "doctor456"));
//		doctors.add(new Doctor(3, "Dr. Mohammad Rahman", "Orthopedic Surgeon", "MBBS; MS", "+8801XXXXXXXXX",
//				"dr.mohammad@example.com", "securepass"));
//		doctors.add(new Doctor(4, "Dr. Tasnim Akter", "Pediatrician", "MBBS;DCH", "+8801XXXXXXXXX",
//				"dr.tasnim@example.com", "pass789"));
//		doctors.add(new Doctor(5, "Dr. Sharmin Chowdhury", "Gynecologist", "MBBS;MD", "+8801XXXXXXXXX",
//				"dr.sharmin@example.com", "p@ssword123"));
//		doctors.add(new Doctor(6, "Dr. Nasir Uddin", "Neurologist", "MBBS;FCPS", "+8801XXXXXXXXX",
//				"dr.nasir@example.com", "secure123"));
//		doctors.add(new Doctor(7, "Dr. Shireen Akter", "Ophthalmologist", "MBBS;MS", "+8801XXXXXXXXX",
//				"dr.shireen@example.com", "passcode456"));
//		doctors.add(new Doctor(8, "Dr. Imran Ahmed", "ENT Specialist", "MBBS;DLO", "+8801XXXXXXXXX",
//				"dr.imran@example.com", "doctor007"));
//		doctors.add(new Doctor(9, "Dr. Farzana Khan", "Psychiatrist", "MBBS;FCPS", "+8801XXXXXXXXX",
//				"dr.farzana@example.com", "psych123"));
//		doctors.add(new Doctor(10, "Dr. Tanveer Hossain", "General Physician", "MBBS", "+8801XXXXXXXXX",
//				"dr.tanveer@example.com", "general456"));
//		doctors.add(new Doctor(11, "Dr. Rakib Hossain", "General Physician", "MBBS", "+8801XXXXXXXXX",
//				"rakib@gmail.com", "12345"));
//
//		return doctors;
//	}
//
//	public static List<Doctor> loadDoctorsDataFromFile() {
//		List<Doctor> doctors = new ArrayList<>();
//
//		try (BufferedReader reader = new BufferedReader(new FileReader(doctorfileName))) {
//			String line;
//			while ((line = reader.readLine()) != null) {
//				String[] parts = line.split(",");
//				if (parts.length == 7) {
//					int id = Integer.parseInt(parts[0]);
//					String name = parts[1];
//					String specialty = parts[2];
//					String qualifications = parts[3];
//					String contactNumber = parts[4];
//					String email = parts[5];
//					String password = parts[6];
//
//					Doctor doctor = new Doctor(id, name, specialty, qualifications, contactNumber, email, password);
//					System.out.println(doctor + "from load");
//					doctors.add(doctor);
//				}
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		return doctors;
//	}
//
//	private static void saveDoctorDataToFile(List<Doctor> doctorList) {
//		try (BufferedWriter writer = new BufferedWriter(new FileWriter(doctorfileName))) {
//			for (Doctor doctor : doctorList) {
//				String doctorData = doctor.getId() + "," + doctor.getName() + "," + doctor.getSpecialty() + ","
//						+ doctor.getQualifications() + "," + doctor.getContactNumber() + "," + doctor.getEmail() + ","
//						+ doctor.getPassword();
//				writer.write(doctorData);
//				writer.newLine(); // Add a newline after each doctor's data
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	static List<Appointment> getSampleAppointments() {
//		List<Appointment> appointments = new ArrayList<>();
//
//		// Add sample appointment data
//		appointments.add(new Appointment(1, getSamplePatients().get(0).getEmail(), getSampleDoctors().get(0).getEmail(),
//				LocalDate.of(2023, 1, 15), // LocalDate without time
//				true, false));
//
//		// Add more appointments
//		appointments.add(new Appointment(2, getSamplePatients().get(1).getEmail(), getSampleDoctors().get(1).getEmail(),
//				LocalDate.of(2023, 2, 20), // LocalDate without time
//				false, false));
//
//		appointments.add(new Appointment(3, getSamplePatients().get(2).getEmail(), getSampleDoctors().get(2).getEmail(),
//				LocalDate.of(2023, 3, 25), // LocalDate without time
//				true, false));
//
//
//		return appointments;
//	}
//
//	public static void saveAppointmentDataToFile(List<Appointment> appointmentList) {
//		try (BufferedWriter writer = new BufferedWriter(new FileWriter(appointmentFileName))) {
//			for (Appointment appointment : appointmentList) {
//				// Format the appointment data as a string
//				String appointmentData = String.format("%d,%s,%s,%s,%b,%b%n", appointment.getAppointmentID(),
//						appointment.getPatientEmail(), appointment.getDoctortEmail(),
//						appointment.getAppointmentDateTime(), appointment.isAppointmentPending(),
//						appointment.isAppointmentRejected());
//
//				// Write the formatted string to the file
//				writer.write(appointmentData);
//			}
//		} catch (IOException e) {
//			// Handle the exception (e.g., log it or throw a custom exception)
//			e.printStackTrace();
//		}
//	}
//
//	public static List<Appointment> loadAppointmentDataFromFile() {
//		List<Appointment> appointments = new ArrayList<>();
//
//		try (BufferedReader reader = new BufferedReader(new FileReader(appointmentFileName))) {
//			String line;
//			while ((line = reader.readLine()) != null) {
//				// Split the line into individual fields
//				String[] fields = line.split(",");
//
//				// Parse the fields and create an Appointment object
//				int appointmentID = Integer.parseInt(fields[0]);
//				String patientEmail = fields[1];
//				String doctorEmail = fields[2];
//				LocalDate appointmentDateTime = LocalDate.parse(fields[3]);
//				boolean isAppointmentPending = Boolean.parseBoolean(fields[4]);
//				boolean isAppointmentRejected = Boolean.parseBoolean(fields[5]);
//
//				// Create the Appointment object
//				Appointment appointment = new Appointment(appointmentID, patientEmail, doctorEmail, appointmentDateTime,
//						isAppointmentPending, isAppointmentRejected);
//
//				// Add the Appointment object to the list
//				appointments.add(appointment);
//			}
//		} catch (IOException e) {
//			// Handle the exception (e.g., log it or throw a custom exception)
//			e.printStackTrace();
//		}
//
//		return appointments;
//	}
//
//	static void makeFileUsingSampleData() {
//
//		File file = new File(doctorfileName);
//		File file2 = new File(appointmentFileName);
//		File file3 = new File(patientfileName);
//		if (file.exists() == false || file2.exists() == false || file3.exists() == false) {
//
//			// Get the sample doctors
//			List<Doctor> doctors = getSampleDoctors();
//
//			saveDoctorDataToFile(doctors);
//
//			// Get the sample patients
//			List<Patient> patients = getSamplePatients();
//
//			savePatientsDataToFile(patients);
//
//			/// appointment
//
//			List<Appointment> appointmentList = getSampleAppointments();
//
//			saveAppointmentDataToFile(appointmentList);
//
//		}
//	}
//
//}
