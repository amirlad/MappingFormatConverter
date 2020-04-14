package mappingsConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Entity to represent ontology JSON MAPPING
 */
public class Mappings {


   
    
   
    
    private String creator;
  
    private String sourceContactInfo;
    
    private String relation;
   
    private String comment;
	
    private List URIs = new ArrayList<>();
    private List Ontos = new ArrayList<>();

    

    public Mappings() {
        super();
    }
    
    
    public Mappings (List Ontos, List URIs,String creator,String sourceContactInfo,String relation,String comment)
    {
        super();
        this.creator =creator;
        this.sourceContactInfo =sourceContactInfo;
        this.relation =relation;
     
        this.comment =comment;
        this.Ontos = Ontos;
        this.URIs = URIs;
       
    }
    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getSourceContactInfo() {
        return sourceContactInfo;
    }

    public void setSourceContactInfo(String sourceContactInfo) {
        this.sourceContactInfo = sourceContactInfo;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

 

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

   
    
   

	 public void add (List Ontos, List URIs,String creator,String sourceContactInfo,String relation,String comment)
	 {    this.add(Ontos,URIs, creator, sourceContactInfo,relation,comment);
    }


	public List getURIs() {
		return URIs;
	}


	public void setURIs(List uRIs) {
		URIs = uRIs;
	}


	public List getOntos() {
		return Ontos;
	}


	public void setOntos(List ontos) {
		Ontos = ontos;
	}



}
