package eltech.DM2020.BigNum;

import java.util.*;
	/**
	* Класс, который позволяет манипулировать с большими целыми числами
	* @version 0.01
	* @author Сычев Александр, Цветков Иван, Хайруллов Айрат, Муродов Ахмад
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
		if(!isPositive && Number.compareTo(new BigN("0")) == 0)
			isPositive = true;
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
	* Проверка на положительность
	*
    * @return boolean - если положительное - вернёт true, иначе - false.
	*
	* @version 1
	* @author Буторина Полина
	*/
	public boolean checkPositive()
	{
		return isPositive;
	}
	
	/**
	* Сложение 2-x больших целых чисел. Вернёт при сложении НОВОЕ большое целое число
	*
    * @param BigN src - число, которое прибавляется к исходному
    * @return BigN result - новое число, получающееся в результате сложения
	*
	* @version 1
	* @author Цветков Иван, Хайруллов Айрат, Муродов Ахмад
	*/

	public BigZ add(BigZ src)
	{
		BigZ result = new BigZ();

		if (this.isPositive == src.isPositive)
		{
			result.Number = this.Number.add(src.Number);
			result.isPositive = this.isPositive;
			return result;
		}
		else
		{
			int compared = this.Number.compareTo(src.Number);
			if (compared > 0)
			{
				result.Number = this.Number.subtract(src.Number);
				result.isPositive = this.isPositive; 
			}
			else if (compared < 0)
			{
				result.Number = src.Number.subtract(this.Number);
				result.isPositive = src.isPositive; 
			}
			else
			{
				result.Number = new BigN("000");
				result.isPositive = true;
			}
		}
		return result;
	}
	
	/**
	* Умножение на -1
	*
    * @return BigZ result - исходное число, умноженное на -1
	*
	* @version 1
	* @author Семенов Алексей
	*/
	public BigZ multiplyByMinusOne()
	{
		BigZ result;
		if(!this.checkPositive())
		{
			result = new BigZ(this.Number.toString());
		}
		else
		{
			String buff = "-" + this.Number.toString();
			result = new BigZ(buff);
		}
		return result;
	}
	
	/**
	* Деление нацело целых чисел
	*
	* Данное деление основано на том, что остаток от деления должен быть положительным, на примерах:
	* 121 = 7*17 + 2
	* 121 = -7*(-18) + 5
	* -121 = 7*(-18) + 5
	* -121 = -7*18 + 5
	*
    * @param BigZ other - число, на которое делится исходное
    * @return BigZ result - результат деления
	*
	* @version 1
	* @author Семенов Алексей
	*/

	public BigZ divide(BigZ other)
	{
		BigZ result;
		if(this.checkPositive())
		{
			if(other.checkPositive())
				result = new BigZ(this.Number.divide(other.Number).toString());
			else
			{
				result = new BigZ(this.Number.divide(other.Number).increment().toString());
				result = result.multiplyByMinusOne();
			}
		}
		else
		{
			if(other.checkPositive())
			{
				result = new BigZ(this.Number.divide(other.Number).increment().toString());
				result = result.multiplyByMinusOne();
			}
			else
				result = new BigZ(this.Number.divide(other.Number).increment().toString());
		}
		return result;
	}
	
	/**
	* Остаток от деления целых чисел
	*
	* Данное вычисление остатка от деления основано на том, 
	* что остаток от деления должен быть положительным, на примерах:
	* 121 = 7*17 + 2
	* 121 = -7*(-18) + 2
	* -121 = 7*(-18) + 5
	* -121 = -7*18 + 5
	*
    * @param BigZ other - число, на которое делится исходное
    * @return BigZ result - остаток от деления
	*
	* @version 1
	* @author Семенов Алексей, Дементьев Дмитрий
	*/

	public BigZ mod(BigZ other)
	{
		BigZ result;
		if(this.checkPositive())
			result = new BigZ(this.Number.mod(other.Number).toString());
		else
			result = new BigZ(other.Number.subtract(this.Number.mod(other.Number)).toString());
		return result;
	}
}