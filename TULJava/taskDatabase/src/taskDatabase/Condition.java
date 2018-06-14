package taskDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Condition{
	
	String[] inquiry;
	String[] patterns = { "(.*) = (.*)",
						"(.*) <= (.*)",
						"(.*) >= (.*)",
						"(.*) < (.*)",
						"(.*) > (.*)",
						"(.*) != (.*)" };
	
	public Condition( String cond ){
		
		this.inquiry = cond.split(", ");
	}
	public boolean isMatchingCondition(Record record)
	{
		for (String temp : this.inquiry )
		{
			if( !this.isMatchingPattern( temp ) )
				return false;
			
			int id = this.getMatchingPatternId( temp );
			Matcher m = this.pregMatchAllById( id, temp  );
			String column = "";
			String value = "";
			while(m.find())
			{
				column =  m.group(1);
				value = m.group(2);
			}
			switch(id)
			{
				case 0: 
					if( !equals(getMatchingColumnValue(column, record), value ) ) return false;
					break;
				case 1: 
					if( !loequals(getMatchingColumnValue(column, record), value ) ) return false;
					break;
				case 2: 
					if( !goequals(getMatchingColumnValue(column, record), value ) ) return false;
					break;
				case 3: 
					if( !islower( getMatchingColumnValue(column, record), value ) ) return false;
					break;
				case 4: 
					if( !isgreater( getMatchingColumnValue(column, record), value ) ) return false;
					break;
				case 5:
					if( !notequals( getMatchingColumnValue(column, record), value ) ) return false;
					break;
				default: 
					return false;
			}
		}
		return true;
	}
	
	public boolean isMatchingPattern( String cond ){
		for (String temp : this.patterns )
			if(cond.matches(temp))
				return true;
			
		return false;
	}
	public Matcher pregMatchAllById( int id, String command ){
		Pattern p = Pattern.compile( this.patterns[ id ] );
        Matcher m = p.matcher( command );

        return m;
	}
	public int getMatchingPatternId( String cond ){
		for( int i = 0; i < this.patterns.length; i++ )
			if( cond.matches(this.patterns[i]) )
				return i;
		
		return -1;
	}
	public String getMatchingColumnValue(String column, Record record)
	{
		int id = -1;
		String [] columns = 
			{
				"[tT][yY][pP][eE]",
				"[sS][eE][rR][iI][aA][lL][nN][uU][mM][bB][eE][rR]",
				"[mM][aA][nN][uU][fF][aA][cC][tT][uU][rR][eE][rR]",
				"[pP][rR][oO][dD][uU][cC][tT][iI][oO][nN][yY][eE][aA][rR]",
				"[mM][eE][cC][hH][aA][nN][iI][cC][sS]",
				"[vV][aA][lL][uU][aA][tT][iI][oO][nN]",
			};
		for(int i = 0; i < columns.length; i++)
			if(column.matches(columns[i]))
				id = i;
		
		switch(id)
		{
			case 0:
				return record.getType();
			case 1:
				return record.getSerialNumber() + "";
			case 2:
				return record.getManufacturer();
			case 3:
				return record.getProductionYear()+"";
			case 4:
				return record.getMechanics();
			case 5:
				return record.getValuation()+"";
			default: return "Swtich threw default";
		}
	}
	/*public String getMatchingcolumnName(String column, Record record)
	{
		int id = -1;
		String [] columns = 
			{
				"[tT][yY][pP][eE]",
				"[sS][eE][rR][iI][aA][lL][nN][uU][mM][bB][eE][rR]",
				"[mM][aA][nN][uU][fF][aA][cC][tT][uU][rR][eE][rR]",
				"[pP][rR][oO][dD][uU][cC][tT][iI][oO][nN][yY][eE][aA][rR]",
				"[mM][eE][cC][hH][aA][nN][iI][cC][sS]",
				"[vV][aA][lL][uU][aA][tT][iI][oO][nN]",
			};
		for(int i = 0; i < columns.length; i++)
			if(column.matches(columns[i]))
				id = i;
		
		switch(id)
		{
			case 0:
				return "type";
			case 1:
				return "serialNumber";
			case 2:
				return "manufacturer";
			case 3:
				return "productionYear";
			case 4:
				return "mechanics";
			case 5:
				return "valuation";
			default: return "getMatchingColumnName: No matching column";
		}
	}*/
	/*public int getMatchingColumnId(String column, Record record)
	{
		int id = -1;
		String [] columns = 
			{
				"[tT][yY][pP][eE]",
				"[sS][eE][rR][iI][aA][lL][nN][uU][mM][bB][eE][rR]",
				"[mM][aA][nN][uU][fF][aA][cC][tT][uU][rR][eE][rR]",
				"[pP][rR][oO][dD][uU][cC][tT][iI][oO][nN][yY][eE][aA][rR]",
				"[mM][eE][cC][hH][aA][nN][iI][cC][sS]",
				"[vV][aA][lL][uU][aA][tT][iI][oO][nN]",
			};
		for(int i = 0; i < columns.length; i++)
			if(column.matches(columns[i]))
				id = i;
		return id;
	}*/
	
	public boolean equals(String one, String two)
	{
		return one.equals(two);
	}
	public boolean loequals(String one, String two)
	{
		return Integer.parseInt( one ) <= Integer.parseInt( two );
	}
	public boolean goequals(String one, String two)
	{
		return Integer.parseInt( one ) >= Integer.parseInt( two );
	}
	public boolean islower(String one, String two)
	{
		return Integer.parseInt( one ) < Integer.parseInt( two );
	}
	public boolean isgreater(String one, String two)
	{
		return Integer.parseInt( one ) > Integer.parseInt( two );
	}
	public boolean notequals(String one, String two)
	{
		return !one.equals(two);
	}
	
}
