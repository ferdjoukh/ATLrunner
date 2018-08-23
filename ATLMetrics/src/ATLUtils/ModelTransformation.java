package ATLUtils;

import java.util.ArrayList;

import org.eclipse.m2m.atl.emftvm.Rule;

/**
 * This class creates an object of type Model Transformation. 
 * Its purpose is to gather all the information about on model transformation in one object.
 * 
 *   
 * @author Adel Ferdjoukh
 *
 */
public class ModelTransformation {
	private String name;
	private String module;
	private String inMM;
	private String inMMRelativePath;
	private String outMM;
	private String outMMRelativePath;
	private ArrayList<String> tools; 
	private ArrayList<MyRule> rules;
	private int maxScore;
	
	public ModelTransformation(String name, String module, String inMM, String inMMRelativePath,
			String outMM, String outMMRelativePath) {
		this.name= name;
		this.module= module;
		this.inMMRelativePath= inMMRelativePath;
		this.outMMRelativePath= outMMRelativePath;
		this.inMM= inMM;
		this.outMM= outMM;
		rules= new ArrayList<MyRule>();
		tools= new ArrayList<String>();
		maxScore=0;
	}
	
	public void addTool(String tool) {
		this.tools.add(tool);
	}
		
	public void addRule(MyRule rule) {
		this.rules.add(rule);
		this.maxScore=this.maxScore+rule.getKind();
	}

	public String getName() {
		return name;
	}

	public String getModule() {
		return module;
	}

	public String getInMMRelativePath() {
		return inMMRelativePath;
	}

	public String getInMM() {
		return inMM;
	}

	public String getOutMMRelativePath() {
		return outMMRelativePath;
	}

	public String getOutMM() {
		return outMM;
	}

	public ArrayList<MyRule> getRules() {
		return rules;
	}

	public void setRules(ArrayList<MyRule> rules) {
		this.rules = rules;
		computeMaxScore();
	}
		
	private void computeMaxScore() {
		
		int sum=0;
		for(MyRule rule: rules) {
			sum=sum+rule.getKind();
		}
		System.out.println("maxScore"+sum);
		this.maxScore=sum;
	}
	
	public int getMaxScore() {
		return maxScore;
	}

	public ArrayList<String> getTools() {
		return tools;
	}

	public void setTools(ArrayList<String> tools) {
		this.tools = tools;
	}
	
	public String toString() {
		return this.getName();
	}
	
	public String prettyPrint() {
		String res="";
		
		res=res+this.name+" [ module:"+ this.module+", "
				+ "meta-models: "+ this.inMM +"("+this.inMMRelativePath+") > "
				+ this.outMM+"("+this.outMMRelativePath+") ]\n";
		
		res=res+"\t Rules: ";
		for(MyRule rule: this.rules) {
			res=res+" "+rule.getName()+"("+rule.getKind()+")";
		}
		res=res+"\n\n";
		return res;
	}
}
