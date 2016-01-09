package com.ibm.tools.utils;

import java.lang.reflect.Field;

import com.ibm.misc.BASE64Decoder;
import com.ibm.tools.portfoliodb.data.ProjAssignmentDetails;
import com.ibm.tools.portfoliodb.data.ProjectDetails;
import com.ibm.tools.portfoliodb.data.UOMDetails;
import com.ibm.tools.portfoliodb.data.UserDetails;

public class CodeGen {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		//generateSrcHTMLFields(ProjAssignmentDetails.class);
		//generateSrcparseToDBObject(ProjAssignmentDetails.class);
		//generateSrcTHAndData(ProjectDetails.class);
		generateSrcUpdatedWith(ProjAssignmentDetails.class);
		//generateSrcObjectCreationFromCSV(ProjAssignmentDetails.class);
		//generateSrctoDBObject(ProjAssignmentDetails.class);
		//generateSrcObjectCreation(ProjAssignmentDetails.class);
		/*BASE64Decoder decoder = new BASE64Decoder();
		byte[] bf = decoder.decodeBuffer("Y2Z0NjZ0ZmM=");
		System.out.println(new String(bf));*/
	}

	private static void generateSrcObjectCreationFromCSV(Class clz) {
		Field[] fields = clz.getDeclaredFields();
		for (Field field : fields) {
			String fieldName = field.getName();
			String setterMethodName = "set"
					+ Character.toUpperCase(fieldName.charAt(0))
					+ fieldName.substring(1);
			String codeTemplate = "objInstance." + setterMethodName
					+ "(csvRow.get(\"\"));";
			System.out.println(codeTemplate);
		}
	}

	private static void generateSrcTHAndData(Class clz) {
		Field[] fields = clz.getDeclaredFields();
		StringBuilder strBldrTH  = new StringBuilder();
		StringBuilder strBldrData  = new StringBuilder();
		
		for (Field field : fields) {
			String fieldName = field.getName();
			String template1 = "<th>"+fieldName+"</th>"+"\n";
			String template2 = "{ \"data\" : \""+fieldName+"\" },"+"\n";
			strBldrTH.append(template1);
			strBldrData.append(template2);
			
		}
		 System.out.println(strBldrTH);
		 System.out.println("\n\n\n\n\n\n");
		 System.out.println(strBldrData);
	}
	
	private static void generateSrcTHAndData(Class clz,String prefix) {
		Field[] fields = clz.getDeclaredFields();
		StringBuilder strBldrTH  = new StringBuilder();
		StringBuilder strBldrData  = new StringBuilder();
		
		for (Field field : fields) {
			String fieldName = field.getName();
			String template1 = "<th>"+fieldName+"</th>"+"\n";
			//String template2 = "{ \"data\" : \""+prefix+"_"+fieldName+"\" },"+"\n";
			String template2 = "{ \"data\" : \""+fieldName+"\" },"+"\n";
			strBldrTH.append(template1);
			strBldrData.append(template2);
			
		}
		 System.out.println(strBldrTH);
		 System.out.println("\n\n\n\n\n\n");
		 System.out.println(strBldrData);
	}
	
	private static void generateSrcAdhoc(Class clz, String prefix) {
		Field[] fields = clz.getDeclaredFields();
		for (Field field : fields) {
			String fieldName = field.getName();
			// String template = "<th>"+fieldName+"</th>";
			// String template = "{ \"data\" : \""+fieldName+"\" },";

			String getterMethodName = "get"
					+ Character.toUpperCase(fieldName.charAt(0))
					+ fieldName.substring(1);
			// parsedObject.setEmpId((String)dbObject.get("empId"));
			// String codeTemplate =
			// "builtObject."+setterMethodName+"(getSafeString(request.getParameter(\""+fieldName+"\")));";
			//String codeTemplate = "retObject.addProperty(\"" + prefix + "_"	+ fieldName + "\",\"\");";
			//String codeTemplate = "retObject.addProperty(\""+prefix+"_"+fieldName+"\","+prefix+"."+getterMethodName+"());";
			String codeTemplate = "this."+ fieldName+"=projDetails."+getterMethodName+"();";
			System.out.println(codeTemplate);
			// System.out.println(template);
		}
	}

	private static void generateSrcObjectCreation(Class clz) {
		Field[] fields = clz.getDeclaredFields();
		for (Field field : fields) {
			String fieldName = field.getName();
			String setterMethodName = "set"
					+ Character.toUpperCase(fieldName.charAt(0))
					+ fieldName.substring(1);
			String getterMethodName = "get"
					+ Character.toUpperCase(fieldName.charAt(0))
					+ fieldName.substring(1);
			// parsedObject.setEmpId((String)dbObject.get("empId"));
			//String codeTemplate =
			//"builtObject."+setterMethodName+"(getSafeString(request.getParameter(\""+fieldName+"\")));";
			String codeTemplate = "getSafeString(projDetails."
					+ getterMethodName + "()).length()>0 &&";
			System.out.println(codeTemplate);
		}
	}

	private static void generateSrcHTMLFields(Class clz) {
		Field[] fields = clz.getDeclaredFields();
		System.out.println("<div>");
		int count =1;
		for (Field field : fields) {
			String fieldName = field.getName();
			String codeTemplate = "<span style=\"display:inline-block;width:40%\"> \n"
					+ "	<label style=\"display:inline-block;width:30%;\" for=\"{0}\">{0}</label> \n"
					+ "	<input type=\"text\" name=\"{0}\" id=\"{0}\" /> \n"
					+ " </span> \n";
			System.out.println(codeTemplate.replace("{0}", fieldName));
			if(count%2==0)
			{
				System.out.println("</div>\n<div><br/></div>\n<div>");
			}
			count++;
		}
		if(count%2==1)
		{
			System.out.println("</div>\n<div><br/></div>\n");
		}

	}

	private static void generateSrcUpdatedWith(Class clz) {
		Field[] fields = clz.getDeclaredFields();
		for (Field field : fields) {
			String fieldName = field.getName();
			String codeTemplate = "this.{0}=(updatedObject.{0}!=null?updatedObject.{0}:this.{0});";
			System.out.println(codeTemplate.replace("{0}", fieldName));
		}
	}

	private static void generateSrctoDBObject(Class clz) {
		Field[] fields = clz.getDeclaredFields();
		for (Field field : fields) {
			String fieldName = field.getName();
			String codeTemplate = "dbObject.put(\"{0}\",this.{0});";
			System.out.println(codeTemplate.replace("{0}", fieldName));
		}
	}

	private static void generateSrcparseToDBObject(Class clz) {
		Field[] fields = clz.getDeclaredFields();
		for (Field field : fields) {
			String fieldName = field.getName();
			String setterMethodName = "set"
					+ Character.toUpperCase(fieldName.charAt(0))
					+ fieldName.substring(1);
			// parsedObject.setEmpId((String)dbObject.get("empId"));
			String codeTemplate = "parsedObject.{0}((String)dbObject.get(\"{1}\"));";
			String genratedSrc = codeTemplate.replace("{0}", setterMethodName);
			genratedSrc = genratedSrc.replace("{1}", fieldName);
			System.out.println(genratedSrc);
		}
	}
	//generateSrctoDBObject(ProjectDetails.class);
	//generateSrcparseToDBObject(ProjectDetails.class);
	//generateSrcUpdatedWith(SubProjectDetails.class);
	//generateSecHTMLFields(ProjectDetails.class);
	//generateSrcHTMLFields(UserDetails.class);
	 //generateSrcHTMLFields(UOMDetails.class);
	//generateSrcUpdatedWith(UOMDetails.class);
	//generateSrcObjectCreation(UOMDetails.class);
	// generateSrcAdhoc(SubProjectDetails.class);
	//generateSrcObjectCreation(UserDetails.class);
	//generateSrctoDBObject(UOMDetails.class);
	//generateSrcparseToDBObject(UOMDetails.class);
	//generateSrcObjectCreation(ProjectAssignment.class);
	//generateSrcObjectCreationFromCSV(SubProjectDetails.class);
	//generateSrcObjectCreationFromCSV(ProjectAssignment.class);
	//generateSrcAdhoc(ProjectAssignment.class,"asgn");
	//generateSrcAdhoc(EmployeeDetails.class,"emp");
	//generateSrcAdhoc(SubProjectDetails.class, "proj");
	//generateSrcTHAndData(UOMDetails.class,"uomDetails");
	//generateSrcTHAndData(EmployeeDetails.class,"emp");
	//generateSrcTHAndData(ProjectAssignment.class,"asgn");
	//generateSrcTHAndData(ProjectAssignment.class);
	//generateSrcObjectCreation(EmployeeDetails.class);
	//generateSrcAdhoc(SubProjectDetails.class,"");

}
