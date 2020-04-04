package eltech.DM2020.BigNum;

import java.util.*;
	/**
	* Класс, который позволяет манипулировать с большими целыми числами
	* @version 0.01
	* @author Сычев Александр
	*/
public class BigZ
{
	/*Целое число - это натуральное + {0}, но ещё есть знак. Само значение представлено в виде Number, а знак isPositive*/
	private BigN Number;
	private boolean isPositive;
	
	/**
	* Конструктор, с помощью которого можно ввести большое целое число
	* Если строка src пустая или null, то бросит исключение
	*
	* @param String src - представление большого натурального числа в виде строки
	*
	* @version 1
	* @author Сычев Александр
	*/
	public BigZ(String src) throws IllegalArgumentException
	{
		if(src == null)
			throw new IllegalArgumentException("Неверный аргемент: строка не может быть не инициализированной");
		if(src.equals(""))
			throw new IllegalArgumentException("Неверный аргемент: строка не может быть пустой");
		src = src.trim();
		if (src.charAt(0) == '-')
			isPositive = false;
		else
			isPositive = true;
		Number = new BigN( isPositive ? src : src.substring(1, src.length()) );
	}
	
	private BigZ(){}
	
	/**
	* Вывод большого целого числа в виде строки
	*
    * @return Представление числа в виде строки
	*
	* @version 1
	* @author Сычев Александр
	*/
	@Override
	public String toString()
	{
		int i;
		String S = "";
		if(!isPositive)
			S += "-";
		S += Number.toString();
		return S;
	}
	
	/**
	* Проверка, является ли число положительным
	*
    * @return 1 - если положительное, 0 - если нет
	*
	* @version 1
	* @author Семенов Алексей
	*/
	public boolean isPositive()
	{
		return isPositive;
	}
	
	/**
	* Модуль числа
	*
    * @return BigZ result - модуль исходного числа
	*
	* @version 1
	* @author Семенов Алексей
	*/
	public BigZ abs()
	{
		if(this.isPositive()) 
			return this;
		String buff = this.toString();
		buff = buff.substring(1,buff.length());
		BigZ result = new BigZ(buff);
		return result;
	}
	
	/**
	* Умножение на -1
	*
    * @return BigZ result - число, умноженное на -1
	*
	* @version 1
	* @author Семенов Алексей
	*/
	public BigZ multiplyByMinusOne()
	{
		if(!this.isPositive()) return this.abs();
		String buff = "-";
		buff += this.toString();
		BigZ result = new BigZ(buff);
		return result;
	}
	
	/**
	* Конвертация из BigZ в BigN, если первое положительное
	*
    * @return BigN result - натуральное число
	*
	* @version 1
	* @author Семенов Алексей
	*/
	public BigN toBigN() throws ArithmeticException
	{
		if(!this.isPositive()) 
			throw new ArithmeticException("Натуральное число не может быть отрицательным!");
		BigN result = new BigN(this.toString());
		return result;
	}
}