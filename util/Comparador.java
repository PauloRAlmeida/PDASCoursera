import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import entity.Usuario;

public class Comparador {
	public static <E> List<Diferenca> rastrearDiferencas(E velho, E novo) throws Exception{
		List<Diferenca> difs = new ArrayList<>();
		
		
		//pegar a referencia da classe de um desses objetos, class tem um tipo generico. 
		Class<?> claSS = velho.getClass();
		//pegar todos os métodos que começam com getters...
		for(Method m: claSS.getMethods()){
			if(m.getName().startsWith("get") && m.getParameterCount() == 0 && m.getReturnType() != void.class){
				Object valorVelho = m.invoke(velho);
				Object valorNovo = m.invoke(novo);
				if(!valorNovo.equals(valorVelho)){
					Diferenca d = new Diferenca(m.getName(),valorNovo,valorVelho);
					difs.add(d);
				}
			}
		}
		if(difs.isEmpty()){
			return null;
		}else{
			return difs;
		}
	}
	
	public static int comparaOrdemListas(List<Usuario> lista1, List<Usuario> lista2) throws Exception{
		int verificacoes =0;
		for(int i = 0; i < lista1.size(); i++){
			if(rastrearDiferencas(lista1.get(i), lista2.get(i)) == null){
				verificacoes++;
			}
		}
		return verificacoes;
	}
}
