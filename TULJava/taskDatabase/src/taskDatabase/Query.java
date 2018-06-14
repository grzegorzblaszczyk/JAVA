package taskDatabase;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Query 
{
	String command;
	String[] patterns = 
		{	
				"[cC][rR][eE][aA][tT][eE] [tT][aA][bB][lL][eE] (.*)",
				"[sS][eE][lL][eE][cC][tT] (.*) [fF][rR][oO][mM] (.*)",
				"[iI][nN][sS][eE][rR][tT] [iI][nN][tT][oO] (.*) [vV][aA][lL][uU][eE][sS] (.*)",
				"[uU][pP][dD][aA][tT][eE] (.*) [sS][eE][tT] (.*) [wW][hH][eE][rR][eE] (.*)",
				"[dD][eE][lL][eE][tT][eE] [fF][rR][oO][mM] (.*) [wW][hH][eE][rR][eE] (.*)",
				"[sS][hH][oO][wW] [iI][nN][tT][eE][rR][fF][aA][cC][eE]"
		};
	
	public Query(String aCommand)
	{
		this.command = aCommand;
	}

	public boolean isMatchingPattern(){
		for (String temp : this.patterns)
			//if( this.matchesPattern( temp ) )
			if(this.command.matches(temp))
				return true;
			
		return false;
	}
	/*	
	public boolean matchesPattern( String pattern ){
        return this.command.matches( pattern );
    }
    */
	public int getMatchingPatternId()
	{
		int id =0;
		for(int i = 0; i < this.patterns.length; i++)
			if(this.command.matches(this.patterns[i]))
				return i;
		
		return id;
	}
	public Matcher pregMatchAllById( int id ){
		Pattern p = Pattern.compile( this.patterns[ id ] );
        Matcher m = p.matcher( this.command );

        return m;
	}
	
	public Map<String,String> convertMatcherToMap(Matcher m){
		Map<String,String> stringList = new HashMap<String,String>();
		int id = this.getMatchingPatternId();
		
		while( m.find() ){
			switch( id ){
				case 0: //CREATE TABLE (.*)
					stringList.put( "tableName", m.group(1) ); 
					break;
				case 1: //SELECT (.*) FROM (.*)
					stringList.put( "columns", m.group(1) );
					stringList.put( "tableName", m.group(2) ); 
					break;
				case 2: //INSERT INTO (.*) VALUES (.*)
					stringList.put( "tableName", m.group(1) );
					stringList.put( "values", m.group(2) ); 
					break;
				case 3: //UPDATE (.*) SET (.*)
					stringList.put("tableName", m.group(1));
					stringList.put("set", m.group(2));
					stringList.put("condition", m.group(3));
					break;
				case 4: //DELETE FROM (.*) WHERE (.*)
					stringList.put("tableName", m.group(1));
					stringList.put("condition", m.group(2));
					break;
				default:
					break;
				
			}
        }
		return stringList;
	}
}
