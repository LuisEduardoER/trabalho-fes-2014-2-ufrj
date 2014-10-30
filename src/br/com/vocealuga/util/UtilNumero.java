package br.com.vocealuga.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.List;
import java.util.UUID;

public class UtilNumero {

	public static final String ZERO = "0";
	public final static int PRECISAO_PADRAO_CALCULO = 30;
	public final static int METODO_ARREDONDAMENTO_PADRAO = BigDecimal.ROUND_HALF_EVEN;
	public final static int METODO_ARREDONDAMENTO_ROUND_DOWN = BigDecimal.ROUND_DOWN;
	public final static int SEM_ARREDONDAMENTO = BigDecimal.ROUND_UNNECESSARY;

	private static SecureRandom random = new SecureRandom();

	/*******************************************************************************************************************
	 * ******************************************** METÓDOS DE VALIDAÇÃO
	 * ********************************************* *
	 ******************************************************************************************************************/

	/**
	 * Verifica se uma String do tipo int.
	 * 
	 * @param intNum
	 *            - String contendo o valor a ser verificado
	 * @return <code>true</code> se for int, se não, retorna <code>false</code>
	 */

	public static boolean isInt(Object intNum) {

		try {
			Integer.parseInt(String.valueOf(intNum));
			return true;

		} catch (RuntimeException e) {
			// para NumberFormatException e NullPointerException
			return false;
		}
	}

	/**
	 * Verifica se o número é um número decimal válido para o BigDecimal
	 * 
	 * @param decimalNum
	 * @return
	 */
	public static boolean isBigDecimal(Object decimalNum) {

		try {
			new BigDecimal(String.valueOf(decimalNum));
			return true;

		} catch (RuntimeException e) {
			// para NumberFormatException e NullPointerException
			return false;
		}
	}

	/**
	 * Verifica se a string é um inteiro positivo (maior que 0)
	 * 
	 * @param intNum
	 *            String com o número
	 * @return Retorna "true" caso seja um inteiro positivo (maior que 0) e
	 *         "false" caso contrário
	 */
	public static boolean isIntPositivo(Object intNum) {

		int numero;

		if (isInt(intNum)) {
			numero = Integer.parseInt(String.valueOf(intNum));

			if (numero > 0) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Verifica se a string é um inteiro não negativo (maior ou igual a 0))
	 * 
	 * @param intNum
	 *            string com o número
	 * @return Retorna "true" caso seja um inteiro não negativo (maior ou igual
	 *         a 0) e "false" caso contrário
	 */
	public static boolean isIntNaoNegativo(String intNum) {

		int numero = 0;

		try {
			numero = Integer.parseInt(intNum);

			if (numero >= 0) {
				return true;
			} else {
				return false;
			}

		} catch (NumberFormatException e) {
			return false;
		}
	}

	/**
	 * Verifica se um número digitado é um número monetário. Um número monetário
	 * deve aceitar o uso de pontos (".") para separar milhares e vírgula (",")
	 * para separar casas decimais. Podemos usar uma, duas ou nenhuma casa
	 * decimal.
	 * 
	 * @param numero
	 *            Número que será verificado
	 * @return "True" caso seja um número monetário e "false" caso contrário
	 */
	public static boolean isNumeroMonetario(String numero) {
		return UtilString.verificarFormato(numero, "^\\d{1,3}(\\.?\\d{3})*(,\\d{1,2})?$");
	}

	/**
	 * Verifica se um número digitado é fracionário. Um número fracionário deve
	 * aceitar o uso de pontos (".") para separar milhares e vírgula (",") para
	 * separar casas decimais. Podemos usar de uma a seis casas decimais, além
	 * da opção de não usar.
	 * 
	 * @param numero
	 *            Número que será verificado
	 * @return "True" caso seja um número fracionário e "false" caso contrário
	 */
	public static boolean isNumero(String numero) {
		return UtilString.verificarFormato(numero, "^(\\-)?(([1-9]{1}\\d{0,2})(\\.?\\d{3})*|0+)(,?\\d+)?$");
	}

	/**
	 * Verifica se um número digitado é fracionário positivo (ou seja, não
	 * inclui zero). Um número fracionário deve aceitar o uso de pontos (".")
	 * para separar milhares e vírgula (",") para separar casas decimais.
	 * Podemos usar de uma a seis casas decimais, além da opção de não usar.
	 * 
	 * @param numero
	 *            Número que será verificado
	 * @return "True" caso seja um número fracionário positivo e "false" caso
	 *         contrário
	 */
	public static boolean isNumeroFracionarioPositivo(String numero) {

		numero = UtilString.retirarSimbolos(numero);

		return UtilString.verificarFormato(numero, "^[0-9]{1}[0-9]{0,3}(\\.?\\d{3})*(,\\d{1,6})?$") && !ZERO.equals(numero);
	}

	/**
	 * Verifica se um número digitado é fracionário não negativo (ou seja,
	 * inclui o zero). Um número fracionário deve aceitar o uso de pontos (".")
	 * para separar milhares e vírgula (",") para separar casas decimais.
	 * Podemos usar de uma a seis casas decimais, além da opção de não usar.
	 * 
	 * @param numero
	 *            Número que será verificado
	 * @return "True" caso seja um número fracionário não negativo e "false"
	 *         caso contrário
	 */
	public static boolean isNumeroFracionarioNaoNegativo(String numero) {

		if (!UtilString.apareceNoMaximoUmaVez(numero, ",")) {
			return false;
		}

		numero = UtilString.retirarSimbolos(numero);
		return UtilString.verificarFormato(numero, "^[0-9]{1}[0-9]{0,3}(\\.?\\d{3})*(,\\d{1,6})?$") || ZERO.equals(numero);
	}

	/**
	 * Verifica se a string passada é um número e que tem no máximo uma vírgula
	 */
	public static boolean isNumber(String numero) {

		if (!UtilString.apareceNoMaximoUmaVez(numero, ",")) {
			return false;
		}

		numero = UtilString.retirarSimbolos(numero);
		return UtilString.verificarFormato(numero, "^-?[0-9]{1}[0-9]{0,3}(\\.?\\d{3})*(,\\d{1,6})?$") || ZERO.equals(numero);
	}

	/**
	 * Verifica se um número digitado é inteiro. Um número inteiro deve aceitar
	 * o uso de pontos (".") para separar milhares e não deve permitir o uso de
	 * vírgula (",").
	 * 
	 * @param numero
	 *            Número que será verificado
	 * @return "True" caso seja um número inteiro e "false" caso contrário
	 */
	public static boolean isNumeroInteiro(String numero) {
		return UtilString.verificarFormato(numero, "^\\d{1,3}(\\.?\\d{3})*$");
	}

	/*******************************************************************************************************************
	 * ******************************************** METÓDOS DE FORMATAÇÃO
	 * ******************************************** *
	 ******************************************************************************************************************/

	public static String formatarNumeroComDuasCasasDecimaisComPonto(Number number) {
		return UtilNumero.formatarNumero(number, true, 2);
	}

	public static String formatarNumeroSemCasasDecimaisComPonto(Number number) {
		return UtilNumero.formatarNumero(number, true, 0);
	}

	/**
	 * @deprecated Usar formatarNumeroComDuasCasasDecimaisComPonto(Number), mas
	 *             é necessário cuidado para garantir que não haverá problema
	 *             com a aceitação do ponto
	 */
	public static String formatarNumeroComDuasCasasDecimaisSemPonto(Number number) {
		return UtilNumero.formatarNumero(number, false, 2);
	}

	/**
	 * Transforma um número em String
	 * 
	 * @param number
	 *            Número que será transformado em String
	 * @param comPonto
	 *            Indica se o número será exibido com ponto para separação de
	 *            milhares
	 * @param qtdeCasasDecimais
	 *            Indica o número de casas decimais que seráo exibidas. Caso
	 *            seja 0 (zero), não mostra casas decimais
	 * @return Uma String com o número na formatação desejada
	 */
	public static String formatarNumero(final Number number, final boolean comPonto, final int qtdeCasasDecimais) {

		final DecimalFormatSymbols dfs = new DecimalFormatSymbols(Util.LOCALE_PT_BR);
		final NumberFormat numberFormat;
		final String partePonto = "#,##";
		final String zero = ZERO;
		final String virgula = ".";
		String padrao = "";

		// se tiver ponto, adiciona a parte do ponto
		if (comPonto) {
			padrao = partePonto;
		}

		// sempre tem "0"
		padrao += zero;

		// se tiver casas decimais...
		if (qtdeCasasDecimais != 0) {

			// ... adiciona uma vírgula (para o Java, um ".")...
			padrao += virgula;

			// ... e um "0" para cada casa decimal
			for (int i = 0; i < qtdeCasasDecimais; i++) {
				padrao += zero;
			}
		}

		numberFormat = new DecimalFormat(padrao, dfs);

		return numberFormat.format(number);
	}
	
	public static BigDecimal formatarNumeroBigDecimal(final Number number, final boolean comPonto, final int qtdeCasasDecimais) {

		return UtilNumero.formatarBigDecimal(formatarNumero(number, comPonto, qtdeCasasDecimais));
	}

	/**
	 * Transforma um número em String. Os milhares são separados por "."
	 * (ponto), a parte decimal é separada por vírgula e é exibido apenas o
	 * número de casas decimais necessário.
	 * 
	 * @param number
	 *            Número que será transformado em String.
	 * @return Uma string representando um número.
	 */
	public static String formatarNumero(final Number number) {

		final String resultado;
		final int qtdeCasasDecimais = calcularQtdeCasasDecimais(number);

		// formata o número separando milharas com "." (ponto) e com a
		// quantidade de casas decimais necessárias
		resultado = formatarNumero(number, true, qtdeCasasDecimais);

		return resultado;
	}
	
	
	/**
	 * Transforma um número em String. Os milhares são separados por "."
	 * (ponto), a parte decimal é separada por vírgula e é exibido apenas o
	 * número de casas decimais necessário.
	 * 
	 * @param number
	 *            Número que será transformado em String.
	 * @return Uma string representando um número.
	 */
	public static String formatarNumeroRetornandoNull(final Number number) {
		
		if(Util.vazio(number)) {
			return null;
		}
		
		final String resultado;
		final int qtdeCasasDecimais = calcularQtdeCasasDecimais(number);
		
		// formata o número separando milharas com "." (ponto) e com a
		// quantidade de casas decimais necessárias
		resultado = formatarNumero(number, true, qtdeCasasDecimais);
		
		return resultado;
	}
	
	/**
	 * Transforma um número em String. Os milhares são separados por "."
	 * (ponto), a parte decimal é separada por vírgula e é exibido apenas o
	 * número de casas decimais necessário.
	 * 
	 * @param number
	 *            Número que será transformado em String.
	 * @return Uma string representando um número.
	 */
	public static String formatarNumero(final Number number, Integer qtdeCasasDecimais) {
		
		final String resultado;
		
		if(Util.vazio(qtdeCasasDecimais)) {
			qtdeCasasDecimais = calcularQtdeCasasDecimais(number);
		}	
		
		// formata o número separando milharas com "." (ponto) e com a
		// quantidade de casas decimais necessárias
		resultado = formatarNumero(number, true, qtdeCasasDecimais);
		
		return resultado;
	}

	/**
	 * Transforma o número em String sem usar ponto para milhar
	 * 
	 * @param number
	 * @return
	 */
	public static String formatarNumeroSemPonto(final Number number) {

		String resultado = null;

		if (Util.preenchido(number)) {

			final int qtdeCasasDecimais = calcularQtdeCasasDecimais(number);

			resultado = formatarNumero(number, false, qtdeCasasDecimais);
		}

		return resultado;
	}

	/**
	 * Calcula a qtde de casas decimais que o número possui
	 * 
	 * @param number
	 * @return
	 */
	private static int calcularQtdeCasasDecimais(final Number number) {

		final int qtdeCasasDecimais; // quantidade de casas decimais do número
		final String[] splitNumero = String.valueOf(number).split("\\."); // cria
																			// um
																			// String[2]
																			// que
																			// separa
																			// a
																			// parte
		// inteira da parte decimal

		// se tiver parte decimal
		if (splitNumero != null && splitNumero.length == 2) {
			// pega a parte decimal do número e retira os zeros à direita, ou
			// seja, os desnecessários
			qtdeCasasDecimais = UtilString.retiraZeroDireita(splitNumero[1]).length();
		} else {
			// se não tiver parte decimal a quantidade é zero.
			qtdeCasasDecimais = 0;
		}
		return qtdeCasasDecimais;
	}
	
	/**
	 * Calcula a qtde de casas decimais que o número possui considerando os zeros a direita
	 * 
	 * @param number
	 * @return
	 */
	public static int calcularQtdeCasasDecimaisComZeroDireita(final Number number) {
		
		if(Util.preenchido(number)) {
			final int qtdeCasasDecimais; // quantidade de casas decimais do número
			final String[] splitNumero = String.valueOf(number).split("\\."); // cria
			// um
			// String[2]
			// que
			// separa
			// a
			// parte
			// inteira da parte decimal
			
			// se tiver parte decimal
			if (splitNumero != null && splitNumero.length == 2) {
				// pega a parte decimal do número e retira os zeros à direita, ou
				// seja, os desnecessários
				qtdeCasasDecimais = splitNumero[1].length();
			} else {
				// se não tiver parte decimal a quantidade é zero.
				qtdeCasasDecimais = 0;
			}
			return qtdeCasasDecimais;
		} else {
			return 0;
		}
	}

	/**
	 * Faz validações comuns para métodos que convertem uma String em um tipo de
	 * dado numérico
	 * 
	 * @param stringNumero
	 *            String que será validada
	 * @throws NumberFormatException
	 *             Caso haja algum problema de validação
	 */
	public static String formatarNumeroString(final String stringNumero) throws NumberFormatException {

		final String stringFormatada;

		if (Util.vazio(stringNumero)) {
			throw new NumberFormatException("String não preenchida");
		}

		// tira todos os pontos da String
		stringFormatada = stringNumero.replace(".", "");

		// converte a virgula em ponto
		if (!UtilString.apareceNoMaximoUmaVez(stringFormatada, ",")) {
			throw new NumberFormatException("Há mais de uma vírgula na string");
		}

		return stringFormatada.replace(",", ".");
	}

	public static float formatarFloat(final String stringNumero) throws NumberFormatException {

		final String stringFormatada = formatarNumeroString(stringNumero);
		final float resultado = Float.parseFloat(stringFormatada);

		if (resultado < 0) {
			throw new NumberFormatException("O número não pode ser negativo");
		}

		return resultado;
	}

	public static double formatarDouble(final String stringNumero) throws NumberFormatException {

		final String stringFormatada = formatarNumeroString(stringNumero);
		final double resultado = Double.parseDouble(stringFormatada);

		if (resultado < 0) {
			throw new NumberFormatException("O número não pode ser negativo");
		}

		return resultado;
	}

	public static BigDecimal formatarBigDecimal(final String stringNumero) throws NumberFormatException {
		return new BigDecimal(formatarNumeroString(stringNumero));
	}

	public static boolean menor(final BigDecimal bigDecimal1, final BigDecimal bigDecimal2) {

		if (bigDecimal1.compareTo(bigDecimal2) == -1) {
			return true;
		}

		return false;
	}

	public static boolean maior(final BigDecimal bigDecimal1, final BigDecimal bigDecimal2) {

		if (bigDecimal1.compareTo(bigDecimal2) == 1) {
			return true;
		}

		return false;
	}

	public static boolean igual(final BigDecimal bigDecimal1, final BigDecimal bigDecimal2) {

		if (bigDecimal1.compareTo(bigDecimal2) == 0) {
			return true;
		}

		return false;
	}

	public static boolean diferente(final BigDecimal bigDecimal1, final BigDecimal bigDecimal2) {

		if (bigDecimal1.compareTo(bigDecimal2) != 0) {
			return true;
		}

		return false;
	}

	/**
	 * Compara se "a" é maior ou igual a "b"
	 * 
	 * @param a
	 *            primeiro número
	 * @param b
	 *            segundo número
	 * @return true se "a" for maior ou igual a "b", e false caso contrário
	 */
	public static boolean maiorIgual(final BigDecimal a, final BigDecimal b) {
		if (a.compareTo(b) >= 0) {
			return true;
		}

		return false;
	}

	/**
	 * Compara se "a" é menor ou igual a "b"
	 * 
	 * @param a
	 *            primeiro número
	 * @param b
	 *            segundo número
	 * @return true se "a" for menor ou igual a "b", e false caso contrário
	 */
	public static boolean menorIgual(final BigDecimal a, final BigDecimal b) {
		if (a.compareTo(b) <= 0) {
			return true;
		}

		return false;
	}

	/**
	 * Retorna a soma de dois objetos BigDecimal
	 * 
	 * @param bigDecimal1
	 * @param bigDecimal2
	 * @return bigDecimal1 + bigDecimal2
	 */
	public static BigDecimal soma(final BigDecimal bigDecimal1, final BigDecimal bigDecimal2) {
		return bigDecimal1.add(bigDecimal2);
	}

	/**
	 * Retorna a soma de objetos BigDecimal
	 * 
	 * @param numeros
	 * @return
	 */
	public static BigDecimal soma(final BigDecimal... numeros) {

		BigDecimal soma = BigDecimal.ZERO;

		for (BigDecimal bigDecimal : numeros) {
			soma = soma(bigDecimal, soma);
		}

		return soma;
	}

	/**
	 * Retorna a subtração de dois objetos BigDecimal
	 * 
	 * @param bigDecimal1
	 * @param bigDecimal2
	 * @return bigDecimal1 - bigDecimal2
	 */
	public static BigDecimal subtrai(final BigDecimal bigDecimal1, final BigDecimal bigDecimal2) {
		return bigDecimal1.subtract(bigDecimal2);
	}

	/**
	 * Retorna o resultado arredondado para multiplicação de dois objetos
	 * BigDecimal
	 * 
	 * @param bigDecimal1
	 * @param bigDecimal2
	 * @return bigDecimal1 * bigDecimal2
	 */
	public static BigDecimal multiplica(final BigDecimal bigDecimal1, final BigDecimal bigDecimal2) {
		return arredonda(bigDecimal1.multiply(bigDecimal2), PRECISAO_PADRAO_CALCULO);
	}
	
	public static BigDecimal multiplicaSemArredondar(final BigDecimal bigDecimal1, final BigDecimal bigDecimal2) {
		return bigDecimal1.multiply(bigDecimal2);
	}
	
	/**
	 * divide o bigDecimal1 pelo bigDecimal2 utilizando o arrendondamento padrão
	 * e mantendo o mesmo número de casas decimais dos objetos BigDecimal
	 * passados.
	 * 
	 * @param bigDecimal1
	 * @param bigDecimal2
	 * @return bigDecimal1 / bigDecimal2
	 */
	public static BigDecimal divideMantendoNumeroCasas(final BigDecimal bigDecimal1, final BigDecimal bigDecimal2) {
		return bigDecimal1.divide(bigDecimal2, METODO_ARREDONDAMENTO_PADRAO);
	}

	/**
	 * divide o bigDecimal1 pelo bigDecimal2 utilizando a precisão passada e com
	 * o arrendondamento padrão
	 * 
	 * @param bigDecimal1
	 * @param bigDecimal2
	 * @return bigDecimal1 / bigDecimal2
	 */
	public static BigDecimal divide(final BigDecimal bigDecimal1, final BigDecimal bigDecimal2) {
		return UtilNumero.divide(bigDecimal1, bigDecimal2, METODO_ARREDONDAMENTO_PADRAO);
	}
	
	public static BigDecimal divideSemArredondar(final BigDecimal bigDecimal1, final BigDecimal bigDecimal2){
		return bigDecimal1.divide(bigDecimal2, PRECISAO_PADRAO_CALCULO, METODO_ARREDONDAMENTO_ROUND_DOWN);
	}

	/**
	 * faz a conta bigDecimal1 divido por bigDecimal2 com a precisão padrão
	 * definida pela constante PRECISAO_PADRAO_CALCULO e arredondada pelo
	 * roundingMode
	 * 
	 * <br>
	 * ROUND_CEILING = Rounding mode to round towards positive infinity. <br>
	 * <br>
	 * ROUND_DOWN = Rounding mode to round towards zero. <br>
	 * <br>
	 * ROUND_FLOOR = Rounding mode to round towards negative infinity. <br>
	 * <br>
	 * ROUND_HALF_DOWN = Rounding mode to round towards "nearest neighbor"
	 * unless both neighbors are equidistant, in which case round down. <br>
	 * <br>
	 * ROUND_HALF_EVEN = Rounding mode to round towards the "nearest neighbor"
	 * unless both neighbors are equidistant, in which case, round towards the
	 * even neighbor. <br>
	 * <br>
	 * ROUND_HALF_UP = Rounding mode to round towards "nearest neighbor" unless
	 * both neighbors are equidistant, in which case round up. <br>
	 * <br>
	 * ROUND_UNNECESSARY = Rounding mode to assert that the requested operation
	 * has an exact result, hence no rounding is necessary. <br>
	 * <br>
	 * ROUND_UP = Rounding mode to round away from zero.
	 */
	public static BigDecimal divide(final BigDecimal bigDecimal1, final BigDecimal bigDecimal2, int roundingMode) {

		if (UtilNumero.igual(BigDecimal.ZERO, bigDecimal1) && maior(bigDecimal2, BigDecimal.ZERO)) {
			return BigDecimal.ZERO;
		}

		return UtilNumero.retiraZerosDireita(bigDecimal1.divide(bigDecimal2, PRECISAO_PADRAO_CALCULO, roundingMode));
	}

	/**
	 * Verifica se o primeiro parametro é multiplo do segundo
	 * 
	 * @param numero
	 * @param multiplo
	 * @return
	 */
	public static Boolean isMultiplo(final Integer a, final Integer b) {
		return a % b == 0;
	}

	/**
	 * Verifica se o primeiro parametro é multiplo do segundo para BigDecimals
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static Boolean isMultiplo(final BigDecimal a, final BigDecimal b) {

		final BigDecimal aFormatado = retiraZerosDireita(a);
		final BigDecimal bFormatado = retiraZerosDireita(b);

		/**
		 * o método divideAndRemainder retorna um array, e a segunda posição
		 * representa o resto
		 */
		return UtilNumero.igual(BigDecimal.ZERO, aFormatado.divideAndRemainder(bFormatado)[1]);
	}

	/**
	 * Calcula a porcentagem do numerador em relação ao denominador. Ex:
	 * numerador = 1 e denominador = 2 retornará 50
	 * 
	 * @param numerador
	 * @param denominador
	 * @return o valor da porcentagem
	 */
	public static Float calcularPorcentagem(final Float numerador, final Float denominador) {

		return (numerador / denominador) * 100;
	}

	/**
	 * Funciona como o método calculaPorcentagem acima, a diferença é que este
	 * recebe e retorna BigDecimal
	 */
	public static BigDecimal calcularPorcentagem(BigDecimal numerador, BigDecimal denominador) {

		return multiplica(divide(numerador, denominador), new BigDecimal(100));
	}
	
	/**
	 * Funciona como o método calculaPorcentagem acima, a diferença é que este
	 * recebe e retorna BigDecimal
	 */
	public static BigDecimal calcularPorcentagemSemArredondar(BigDecimal numerador, BigDecimal denominador) {

		return multiplicaSemArredondar(divideSemArredondar(numerador, denominador), new BigDecimal(100));
	}
	

	/**
	 * Retorna a porcentagem formatada com 2 casas decimais
	 * 
	 * @param numerador
	 * @param denominador
	 * @return
	 */
	public static String obterPorcentagemFormatada(final BigDecimal numerador, final BigDecimal denominador) {

		if (UtilNumero.maior(numerador, BigDecimal.ZERO) && UtilNumero.maior(denominador, BigDecimal.ZERO)) {

			return formatarNumeroComDuasCasasDecimaisComPonto(multiplica(divide(numerador, denominador), new BigDecimal("100")));

		} else {
			return "0";
		}
	}

	/**
	 * Calcula o resultado da porcentagem aplicada a um número. Ex: numero = 10
	 * e porcentagem = 100 retornará o próprio número 10. numero = 10 e
	 * porcentagem = 80 retornará 8.
	 * 
	 * @param numero
	 * @param porcentagem
	 *            valor da porcentagem multiplicada por 100 (ex: 78, 68.5, 19,
	 *            etc)
	 * @return o numerador da porcentagem
	 */
	public static BigDecimal obterResultadoPorcentagem(final BigDecimal numero, final BigDecimal porcentagem) {

		return multiplica(numero, divide(porcentagem, new BigDecimal("100")));
	}

	/**
	 * Retira os zeros a direita da parte fracionária. O número 119.1000 ficará
	 * 119.1
	 * 
	 * @param numero
	 * @return
	 */
	public static BigDecimal retiraZerosDireita(final BigDecimal bigDecimal) {

		if (Util.preenchido(bigDecimal)) {

			String numero = bigDecimal.toPlainString();
			String novoNumero = numero;
			final Integer indiceDecimal = novoNumero.indexOf(".");

			if (indiceDecimal > 0) {

				for (int i = numero.length() - 1; i > indiceDecimal; i--) {

					if (numero.charAt(i) == '0') {
						novoNumero = numero.substring(0, i);

					} else {
						break;
					}
				}
			}

			return new BigDecimal(novoNumero);

		} else {
			return null;
		}
	}

	/**
	 * Arredonda o bigDecimal passado para o número de casas.
	 * 
	 * OBS.: Esse método faz uso do método retiraZerosDireita no resultado.
	 **/
	public static BigDecimal arredonda(BigDecimal numero, int casas) {
		return retiraZerosDireita(numero.setScale(casas, UtilNumero.METODO_ARREDONDAMENTO_PADRAO));
	}

	/**
	 * Arredonda o bigDecimal passado para o número de casas.
	 * 
	 * OBS.:Quando o número de casas não é passado o método usa o valor da
	 * constante UtilNumero.PRECISAO_EXIBICAO.
	 **/
	public static BigDecimal arredonda(BigDecimal numero) {

		int PRECISAO_PADRAO_EXIBICAO = 3 ;
		int NUMERO_CASAS_DECIMAIS_APOS_ZERO = 2;

		String numeroFormatado = numero.toPlainString();
		numero = arredonda(numero, PRECISAO_PADRAO_EXIBICAO);
		final int posicaoVirgula = numeroFormatado.indexOf(".");
		int qtdeIteracao = 0;

		for (int i = posicaoVirgula + 1; i < numeroFormatado.length(); i++) {

			if (numeroFormatado.charAt(i) != '0') {
				break;
			}
			qtdeIteracao++;
		}

		if (qtdeIteracao + NUMERO_CASAS_DECIMAIS_APOS_ZERO < PRECISAO_PADRAO_EXIBICAO) {

			numero = arredonda(numero, qtdeIteracao + NUMERO_CASAS_DECIMAIS_APOS_ZERO);

		}

		return numero;
	}

	/**
	 * Arredonda o bigDecimal passado para o bigDecimal equivalente ao inteiro
	 * mais próximo.
	 * 
	 * @param numero
	 * @return BigDecimal
	 */
	public static BigDecimal arredondaParaInteiroMaisProximo(BigDecimal numero) {

		numero = numero.setScale(0, BigDecimal.ROUND_HALF_UP);

		return numero;
	}

	/**
	 * Arredonda o bigDecimal passado para o bigDecimal equivalente ao inteiro
	 * mais próximo acima.
	 * 
	 * @param numero
	 * @return BigDecimal
	 */
	public static BigDecimal arredondaParaInteiroAcima(BigDecimal numero) {

		numero = numero.setScale(0, BigDecimal.ROUND_CEILING);

		return numero;
	}

	/**
	 * Arredonda o bigDecimal passado para o bigDecimal equivalente ao inteiro
	 * mais próximo abaixo.
	 * 
	 * @param numero
	 * @return BigDecimal
	 */
	public static BigDecimal arredondaParaInteiroAbaixo(BigDecimal numero) {

		numero = numero.setScale(0, BigDecimal.ROUND_DOWN);

		return numero;
	}

	/**
	 * Retorna o primeiro número, menor que "numero" e que seja múltiplo de
	 * "valorMultiplo".
	 * 
	 * @param numero
	 * @param valorMultiplo
	 * @return
	 */
	public static BigDecimal multiploAnterior(BigDecimal numero, BigDecimal valorMultiplo) {
		return subtrai(numero, numero.divideAndRemainder(valorMultiplo)[1]);
	}

	public static BigDecimal multiploPosterior(BigDecimal numero, BigDecimal valorMultiplo) {

		if (isMultiplo(numero, valorMultiplo)) {
			return numero;
		}

		return soma(numero, subtrai(valorMultiplo, numero.divideAndRemainder(valorMultiplo)[1]));
	}

	/**
	 * Retorna o menor número entre dois passados como parâmetro.
	 * 
	 * @param valor1
	 * @param valor2
	 * @return
	 */
	public static BigDecimal min(BigDecimal valor1, BigDecimal valor2) {
		BigDecimal menor = valor1;
		if (maior(valor1, valor2)) {
			menor = valor2;
		}
		return menor;
	}

	/**
	 * Retorna o múltiplo mais proximo de "numero". Se a "distância" entre os
	 * múltiplos for igual, retorna o menor.
	 * 
	 * @param numero
	 * @param bigDecimal2
	 * @return
	 */
	public static BigDecimal multiploMaisProximo(BigDecimal numero, BigDecimal valorMultiplo) {

		final BigDecimal anterior = multiploAnterior(numero, valorMultiplo);
		final BigDecimal posterior = multiploPosterior(numero, valorMultiplo);

		final BigDecimal distanciaPosterior = subtrai(posterior, numero);
		final BigDecimal distanciaAnterior = subtrai(numero, anterior);

		if (menor(distanciaPosterior, distanciaAnterior)) {
			return posterior;
		} else {
			return anterior;
		}
	}

	/**
	 * Converte o número passado para BigDecimal
	 */
	public static BigDecimal converterParaBigDecimal(Number numero) {

		return formatarBigDecimal(formatarNumero(numero));
	}

	/**
	 * Calcula a media de uma lista de valoresu
	 */
	public static BigDecimal calculaMedia(List<BigDecimal> valores) {

		BigDecimal somatorio = BigDecimal.ZERO;

		for (BigDecimal valor : valores) {

			somatorio = UtilNumero.soma(somatorio, valor);
		}

		if (valores.size() > 0) {

			return UtilNumero.divide(somatorio, UtilNumero.converterParaBigDecimal(valores.size()));
		} else
			return BigDecimal.ZERO;
	}

	/**
	 * Obtém o valor mínimo de uma lista de valores passada como parâmetro
	 */
	public static BigDecimal obtemValorMinimo(List<BigDecimal> valores) {

		if (Util.preenchido(valores)) {

			BigDecimal valorMinimo = new BigDecimal(Integer.MAX_VALUE);

			for (BigDecimal valor : valores) {

				if (UtilNumero.menor(valor, valorMinimo)) {
					valorMinimo = valor;
				}
			}

			return valorMinimo;
		} else
			return BigDecimal.ZERO;
	}

	/**
	 * Obtém o valor máximo de uma lista de valores passada como parâmetro
	 */
	public static BigDecimal obtemValorMaximo(List<BigDecimal> valores) {

		if (Util.preenchido(valores)) {

			BigDecimal valorMaximo = new BigDecimal(Integer.MIN_VALUE);

			for (BigDecimal valor : valores) {

				if (UtilNumero.maior(valor, valorMaximo)) {
					valorMaximo = valor;
				}
			}

			return valorMaximo;
		} else
			return BigDecimal.ZERO;
	}

	public static boolean isNumeroValido(Number numero) {

		try {

			formatarNumero(numero);
			return true;

		} catch (Exception e) {

			return false;
		}

	}
	
	/**
	 * Determina a quantidade de casas decimais do campo de acordo com o valor passado por parâmetro.
	 * @param campo
	 * @param qtdeCasasDecimais
	 * @return
	 */
	public static String determinaQtdeCasasDecimais(String campo, Integer qtdeCasasDecimais){
		
		String resultado = null;
		
		if(Util.preenchido(campo) && UtilNumero.isNumber(campo)) {
			int escala = 0;
			
			final BigDecimal campoBig = new BigDecimal(campo);
			
			if(Util.preenchido(qtdeCasasDecimais)) {
				
				escala = qtdeCasasDecimais;
				
				resultado = UtilNumero.formatarNumero(campoBig, false, escala);

			} else {
				resultado = UtilNumero.formatarNumero(UtilNumero.arredonda(new BigDecimal(campo))); 
			}
			return resultado;
		}
		return campo;
	}
	
	public static String proximaString()
	  {
	    return new BigInteger(130, random).toString();
	  }
	
	public static String proximoUUID(){
		String uuid = UUID.randomUUID().toString();
		return uuid;
	}
	
	public static double gerarNumeroAleatorio(){
				
		return random.nextDouble();
	}
}