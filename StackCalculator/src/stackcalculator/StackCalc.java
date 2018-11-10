import java.util.ArrayList;
import java.util.Stack;

import org.omg.CORBA.portable.ValueInputStream;

public class StackCalc 
{
      static String validOperands = "0123456789";
      static String validOperators = "*()+-%/";
      static boolean failed = false;
      /**
       * 
       **/
      public static void main(String[] args) 
      {
            Stack<String> operators = new Stack<String>();
            Stack<String> operands = new Stack<String>();
            Stack<String> postfix = new Stack<String>();
            // if there are less than 2 args there is no operation 
            //that can be performed
            if (args.length > 2) 
            {
                  {
                        for (int i = 0; i < args.length; i++)
                        {
                              postfix.push(args[i]);
                        }
                  }
                  else
                  {
                        for (int i = 0; i < args.length; i++)
                        {
                              if (validOperands.contains(args[i]))
                              {
                                    operands.push(args[i]);
                                    
                              }
                              else if (validOperators.contains(args[i]))
                              {
                                    if (args[i].equals(")"))
                                    {
                                          while (!operators.peek().equals("("))
                                          {
                                                operands.push(operators.pop());
                                          }
                                          operators.pop();
                                    }
                                    else
                                    {
                                          operators.push(args[i]);
                                    }
                              }
                              else
                              {
                                    failed = true;
                              }
                        }
                        for (String i : operators)
                        {
                              operands.push(i);
                        }
                  }
                  
                  while (!operands.isEmpty())
                  {
                        System.out.print(operands.peek());
                        postfix.push(operands.pop());
                  }
                  System.out.println("");
                  double result = calculate(postfix);
                  //
                  if (!failed && Double.isFinite(result))
                  {
                        System.out.println("result : " + result);
                        System.out.println("status code:" + 0);
                  }
                  else
                  {
                        System.out.println("result : " + result);
                        System.out.println("status code:" + 2);
                  }
                  
            }
            else
            {
                  System.out.println("status code:" + 1);
            }
      }
      
      /**
       * calculates the postfix expression based on postfix passedin
       * @param postfix the postfix to evaluate
       * @return the result of the equation
       **/
      public static double calculate(Stack<String> postfix)
      {
            Stack<Double> result = new Stack<Double>();
            Double tempresult;
            while (!postfix.isEmpty())
            {
                  if (validOperands.contains(postfix.peek()))
                  {
                        result.push(Double.parseDouble(postfix.pop()));
                  }
                  else if (validOperators.contains(postfix.peek()))
                  {
                        switch (postfix.pop())
                        {
                              case"+":
                                    tempresult = result.pop() + result.pop();
                                    result.push(tempresult);
                                    break;
                              case"-":
                                    tempresult = result.pop() - result.pop();
                                    result.push(tempresult);
                                    break;
                              case"*":
                                    tempresult = result.pop() * result.pop();
                                    result.push(tempresult);
                                    break;
                              case"/":
                                    double a = result.pop();
                                    double b = result.pop();
                                    try
                                    {
                                          tempresult = b / a;
                                          result.push(tempresult);
                                    }
                                    catch (ArithmeticException e)
                                    {
                                          failed = true;
                                    }
                                    break;
                              case"%":
                                    double c = result.pop();
                                    double d = result.pop();
                                    tempresult = d % c;
                                    result.push(tempresult);
                                    break;
                        }
                  }
            }
            return result.pop();
               
      }
     
}
