package com.kruger.inventario.utils;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.kruger.inventario.model.Empleado;
import com.kruger.inventario.model.Rol;
import com.kruger.inventario.model.Usr;
import com.kruger.inventario.repository.Rol_R;
import com.kruger.inventario.repository.User_R;

@Component
public class Validations {

	Logger logger = LoggerFactory.getLogger(Validations.class);
	
	@Autowired
	User_R userRepository;
	
	@Autowired
	Rol_R rolRepository;

	public boolean validarCedula(String cedula) {
		boolean cedulaCorrecta = false;
		try {
			if (cedula.length() == 10) // ConstantesApp.LongitudCedula
			{
				int tercerDigito = Integer.parseInt(cedula.substring(2, 3));
				if (tercerDigito < 6) {
					int[] coefValCedula = { 2, 1, 2, 1, 2, 1, 2, 1, 2 };
					int verificador = Integer.parseInt(cedula.substring(9, 10));
					int suma = 0;
					int digito = 0;
					for (int i = 0; i < (cedula.length() - 1); i++) {
						digito = Integer.parseInt(cedula.substring(i, i + 1)) * coefValCedula[i];
						suma += ((digito % 10) + (digito / 10));
					}

					if ((suma % 10 == 0) && (suma % 10 == verificador)) {
						cedulaCorrecta = true;
					} else if ((10 - (suma % 10)) == verificador) {
						cedulaCorrecta = true;
					} else {
						logger.error("El numero de cedula es incorrecta");
						cedulaCorrecta = false;
					}
				} else {
					cedulaCorrecta = false;
					logger.error("La cedula ingresada es incorrecta");
				}
			} else {
				logger.error("El numero de cedula esta incompleto");
				cedulaCorrecta = false;
			}
		} catch (NumberFormatException nfe) {
			cedulaCorrecta = false;
		} catch (Exception err) {
			logger.error("La cedula ingresada es incorrecta");
			cedulaCorrecta = false;
		}

		if (!cedulaCorrecta) {
			System.out.println("La Cédula ingresada es Incorrecta");
		}
		logger.info("Cedula correcta " + cedula);
		return cedulaCorrecta;
	}

	public boolean validarCaracteres(String cadena) {
		if (cadena.length() > 0) {
			for (int x = 0; x < cadena.length(); x++) {
				char c = cadena.charAt(x);
				if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == ' ')) {
					logger.error("Cadena invalida " + cadena);
					return false;
				}
			}
			return true;
		}
		else {
			logger.error("Cadena invalida " + cadena);
			return false;
		}
	}

	public boolean validarCorreo(String email) {
		Pattern pattern = Pattern.compile(
				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

		Matcher mather = pattern.matcher(email);

		if (mather.find() == true) {
			return true;
		} else {
			logger.error("El email ingresado es incorrecto " + email);
			return false;
		}
	}

	public boolean validarNumeros(String cadena, String tipo) {
		if((cadena.length() == 10) && ("celular".equalsIgnoreCase(tipo)) ||((cadena.length() <= 10) && ("dosis".equalsIgnoreCase(tipo)))) {
			for (int x = 0; x < cadena.length(); x++) {
				char c = cadena.charAt(x);
				if (!(c >= '0' && c <= '9')) {
					logger.error("El numero ingresado es incorrecto " + cadena);
					return false;
				}
			}
			return true;
		}
		else 
			return false;
	}
	
	public boolean validarFecha(Date fecha_in) {
	    boolean correcto = false;
	    long now = System.currentTimeMillis();
	    
		Date fecha_actual = new Date(now);
		
		if (fecha_in.before(fecha_actual))
			correcto = true;
		else {
			correcto = false;
			logger.error("la fecha ingresada es incorrecta " + fecha_in.toString());
		}	
	    return correcto;
	}

	public HashMap<String, Object> generarCredendiales(Empleado empleado) {
		Usr ci = userRepository.findByCedula(empleado.getCedula());
		HashMap<String, Object> resultado = new HashMap<>();
		if (ci == null) {
			String usuario = empleado.getNombre().trim().toLowerCase() + "."+empleado.getApellido().trim().toLowerCase();
			resultado.put("usuario",usuario);
			String password = usuario + "." +LocalDate.now().getYear();
			resultado.put("password",password);
			password = passwordEncoder().encode(password);
			List<Rol> roles = new ArrayList<>();
			Optional<Rol> rolOp = rolRepository.findById(2);
			Rol rol = rolOp.get();	
			roles.add(rol);
			Usr user = new Usr(usuario,password, empleado.getCedula(), roles);
			userRepository.save(user);
			return resultado;			
		}
		else {
		if(!(empleado.getCedula().equals(ci.getId_empleado())))
		{
			String usuario = empleado.getNombre().trim().toLowerCase() + "."+empleado.getApellido().trim().toLowerCase();
			resultado.put("usuario",usuario);
			String password = usuario + "." +LocalDate.now().getYear();
			resultado.put("password",password);
			password = passwordEncoder().encode(password);
			List<Rol> roles = new ArrayList<>();
			Optional<Rol> rolOp = rolRepository.findById(2);
			Rol rol = rolOp.get();	
			roles.add(rol);
			Usr user = new Usr(usuario,password, empleado.getCedula(), roles);
			userRepository.save(user);
			return resultado;
		}}
		
		resultado.put("usuario","El usuario ya se encuentra creado");
		return resultado;
	}	
	
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }	
}