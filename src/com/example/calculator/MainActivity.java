package com.example.calculator;






import android.support.v7.app.ActionBarActivity;


import android.text.Editable;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
class Stack {
    private int maxSize;
    private char[] stackArray;
    private int top;
    public Stack(int max) {
       maxSize = max*max;
       stackArray = new char[maxSize];
       top = -1;
      
    }
    public void push(char j) {
       stackArray[++top] = j;
    }
    public char pop() {
       return stackArray[top--];
    }
    public char peek() {
       return stackArray[top];
    }
    public boolean isEmpty() {
       return (top == -1);
   }
 }

class InToPost {
	   private Stack theStack;
	   private String input;
	   private String output= "" ;
	   public InToPost(String in) {
	      input = in;
	      int stackSize = input.length();
	      theStack = new Stack(stackSize);
	   }
	   public String doTrans() {
	      for (int j = 0; j < input.length(); j++) {
	         char ch = input.charAt(j);
	         switch (ch) {
	            case '+': 
	            case '-':
	            output = output+' ';
	            gotOper(ch, 1); 
	            break; 
	            case 'x': 
	            case '/':
	            output = output+' ';
	            gotOper(ch, 2); 
	            break; 
	            case '(': 
	            		
	            theStack.push(ch);
	            break;
	            case ')':
	           output = output+' ';
	            gotParen(ch); 
	            break;
	            default: 
	            output = output + ch; 
	           
	            break;
	         }
	      }
	     // output = output+' ';
	      while (!theStack.isEmpty()) {
	    	  output = output+' ';
	         output = output + theStack.pop();
	         
	      }
	      
	      return output; 
	   }
	   public void gotOper(char opThis, int prec1) {
	      while (!theStack.isEmpty()) {
	         char opTop = theStack.pop();
	         if (opTop == '(') {
	            theStack.push(opTop);
	            break;
	         }
	         else {
	            int prec2;
	            if (opTop == '+' || opTop == '-')
	            prec2 = 1;
	            else
	            prec2 = 2;
	            if (prec2 < prec1) { 
	               theStack.push(opTop);
	               break;
	            }
			    else
			    		
	            output = output + opTop;
	            output = output+' ';
	            }
	         
	      }
	      theStack.push(opThis);
	   }
	   public void gotParen(char ch){
		    
	      while (!theStack.isEmpty()) {
	         char chx = theStack.pop();
	         
	         if (chx == '(') 
	         break; 
	         else
	         
	         output = output + chx; 
	      }
	      
	      
	   }
}

class sStack  
{  
   private double[] a;  
   private int top,Size;  
   public sStack(int max)  
   {  
     Size = 0; 
     a=new double[max];  
     top=0;  
   }  
   public void push(double key)  
   {  
     a[top]=key; 
     top++;
     Size++;
   }  
   public void pop()  
   {  
     top--;Size--;  
   }  
   double gettop()
   {
	return a[top-1];
	   
   }
}
class Evaluation{  
	   public double calculate(String s)  
	   {  
	     int n;
	     double r=0;  
	     n=s.length(); 
	     
	     sStack a=new sStack(n);  
	     for(int i=0;i<n;)  
	     {  
	       char ch=s.charAt(i);  
	       if (ch == ' ')
	       {
	    	   i++;
	    	   continue;
	       }
	       else if (ch >='0' && ch <='9' || ch =='.')
	       {
	    	   String p = new String();
	    	   while (ch!= ' ')
	    	   {
	    		   
	    		   p= p+ch;
	    		   i++;
	    		   ch = s.charAt(i);
	    	   }
	    	  
	    	   double myNum = 0;

	    	   try {
	    	       myNum = Double.parseDouble(p);
	    	   } catch(NumberFormatException nfe) {
	    	      System.out.println("Could not parse " + nfe);
	    	   }
	    	   a.push(myNum);
	       }
	       
	       else
	       {
	    	 i++;
	    	 if (ch == '+')
             {
                 double op2 = a.gettop();
                 
                 a.pop();
                 double op1 = a.gettop();
                a.pop();
                double rslt = op1+op2;
                 a.push(rslt);

             }
             else if (ch == '-')
             {
                 double op2 = a.gettop();
                 a.pop();
                 double op1 = a.gettop();
                 a.pop();
                 double rslt = op1-op2;
                 a.push(rslt);

             }
             else if (ch == 'x')
             {
                 double op2 = a.gettop();
                 a.pop();
                 double op1 = a.gettop();
                 a.pop();
                 double rslt = op1*op2;
                 a.push(rslt);

             }
             else if (ch == '/')
             {
                 double op2 = a.gettop();
                 a.pop();
                 double op1 = a.gettop();
                 a.pop();
                double rslt = op1/op2;
                 a.push(rslt);

             }
	       }
	       
	       
	      
	     }  
	     r=a.gettop();  
	     return(r);  
	   }  
	}  


public class MainActivity extends ActionBarActivity {
	
	Button dot,one,two,three,four,five,six,seven,eight,nine,zero,plus,minus,div,mul,equal,cut,clear,fparen,lparen,lv,hv;
	EditText display;
	TextView txt;
	String written;
	String ini;
	String calc;
	int swtch=0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		if (getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_PORTRAIT) {
        setContentView(R.layout.activity_main);
    } else {
        setContentView(R.layout.lnd);
    }
		
		
		
		initialize();
		//display.setTextAlignment(MODE_APPEND);
		   ini = display.getText().toString();
		three.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//written = display.getText().toString();
				display.append("3");
				
				
				
				
			}
		});
		one.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				display.append("1");
				
				
				
			}
		});
		two.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				display.append("2");
				
				
			}
		});
		four.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				display.append("4");
			}
		});
		five.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				display.append("5");
			}
		});
		six.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				display.append("6");
			}
		});
		seven.setOnClickListener(new View.OnClickListener() {
	
			@Override
		public void onClick(View arg0) {
		// TODO Auto-generated method stub
				
				display.append("7");
				 
			}
		});
		eight.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				display.append("8");
			
			}
		});
		nine.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				display.append("9");
			}
		});
		zero.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				display.append("0");
				
			}
		});
		dot.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				display.append(".");
			}
		});
		plus.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				display.append("+");
			}
		});
		minus.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				display.append("-");
			}
		});
		mul.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				display.append("x");
			}
		});
		fparen.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
					{
						display.append("(");
						swtch = 1;
					}
				
			}
		});
		lparen.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				display.append(")");
				swtch=0;
				txt.setText("");

			}
		});
		div.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				display.append("/");
			}
		});
		if( cut != null ) {
			   cut.setOnClickListener(new View.OnClickListener() {
			      @Override
			      public void onClick(View arg0) {
			    	  txt.setText("");
			         String textString = display.getText().toString();
			         if( textString.length() > 0 ) {
			            display.setText(textString.substring(0, textString.length() - 1 ));
			            display.setSelection(display.getText().length());//position cursor at the end of the line
			         }
			      }
			   });
			}
		
		clear.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				display.setText(null);
				txt.setText("");
				swtch = 0;
				
			}
		});
		
		lv.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				if (getResources().getConfiguration().orientation ==
		                Configuration.ORIENTATION_PORTRAIT){setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		                }
				else
				{
					setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
				}
			}
		});
		
		
		equal.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (swtch == 0){
					txt.setText("");
				written= display.getText().toString();
				
				/*InToPost theTrans = new InToPost(written);
			      calc = theTrans.doTrans();
			      //display.setText(calc);
			      Evaluation e=new Evaluation();  
			      display.setText(" " + e.calculate(calc)); */
			     
				 if (written.length()==0){
					 display.setText(null);
					 
					 }
			      else{
			    	  int cntr = 0;
			    	  for (int i=0;i<written.length();i++)
			    	  {
			    		  char ch = written.charAt(i);
			    		  if (ch == '+' || ch == '-' || ch =='x' || ch == '/') cntr++;
			    	  }
			    	  if (cntr == 0) display.setText(written);
			    	  else {
			      	InToPost exp = new InToPost (written);
					
					Evaluation ev = new Evaluation();
					double nb =  ev.calculate(exp.doTrans());
					if (nb- (int)nb == 0) display.setText("= "+ (int)nb);
					else
					display.setText("= "+ ev.calculate(exp.doTrans()));
			    		  
			      }
			      }
				 
			      
			      
					
			    	   
			}	
				else
				{
					txt.setText("Warning : Complete Bracket");
				}
			      
			}
		});
		
		
		
	}
	
	

	

	private void initialize() {
		// TODO Auto-generated method stub
		one = (Button) findViewById (R.id.button7);
		eight = (Button) findViewById (R.id.button2);
		three = (Button) findViewById (R.id.button9);
		zero = (Button) findViewById (R.id.button11);
		dot = (Button) findViewById (R.id.button10);
		four = (Button) findViewById (R.id.button3);
		minus = (Button) findViewById (R.id.button15);
		plus = (Button) findViewById (R.id.button16);
		two = (Button) findViewById (R.id.button8);
		display = (EditText) findViewById (R.id.editText1);
		div = (Button) findViewById (R.id.button13);
		mul = (Button) findViewById (R.id.button14);
		six = (Button) findViewById (R.id.button6);
		equal = (Button) findViewById (R.id.button12);
		five = (Button) findViewById (R.id.button4);
		seven = (Button) findViewById (R.id.button1);
		cut = (Button) findViewById (R.id.button17);
		nine = (Button) findViewById (R.id.button5);
		clear = (Button) findViewById (R.id.button18);
		fparen = (Button) findViewById (R.id.button19);
		lparen = (Button) findViewById (R.id.button20);
		txt = (TextView) findViewById (R.id.textView1);
		lv = (Button) findViewById (R.id.cv);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	public void onConfigurationChanged(Configuration newConfig) {
	    super.onConfigurationChanged(newConfig);

	    int orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
	    // or = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
	    setRequestedOrientation(orientation);
	    
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
