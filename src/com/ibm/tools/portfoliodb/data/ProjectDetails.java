package com.ibm.tools.portfoliodb.data;

import java.util.HashMap;
import java.util.Map;

import org.bson.types.ObjectId;

import com.ibm.tools.dbacess.JSONSerializable;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class ProjectDetails implements JSONSerializable {

	private ObjectId id;
	private long uomId;
	private String projectName;
	private String projMgr;
	private String dpe;
	private String blueCommunity;
	private String bam;
	private String director;
	private String projType;
	private String projPrimTech;
	private String domain;
	private String deleteFg;
	private String flm;
	private String slm;
	private String l1ex;
	private String l2ex;
	
	//Following are internal details of the project
	private String objIdHexString;
	private long projectId;//Internal
	private Map<String,String> displayFileds;
	
	//Adding reference objects 
	private UOMDetails uomDetails;
	
	public ProjectDetails()
	{
		super();
	}
	
	@Override
	public String toJSON() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return the id
	 */
	public ObjectId getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(ObjectId id) {
		this.id = id;
	}

	/**
	 * @return the uomId
	 */
	public long getUomId() {
		return uomId;
	}

	/**
	 * @param uomId the uomId to set
	 */
	public void setUomId(long uomId) {
		this.uomId = uomId;
	}

	/**
	 * @return the projectName
	 */
	public String getProjectName() {
		return projectName;
	}

	/**
	 * @param projectName the projectName to set
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	/**
	 * @return the projMgr
	 */
	public String getProjMgr() {
		return projMgr;
	}

	/**
	 * @param projMgr the projMgr to set
	 */
	public void setProjMgr(String projMgr) {
		this.projMgr = projMgr;
	}

	/**
	 * @return the dpe
	 */
	public String getDpe() {
		return dpe;
	}

	/**
	 * @param dpe the dpe to set
	 */
	public void setDpe(String dpe) {
		this.dpe = dpe;
	}

	/**
	 * @return the blueCommunity
	 */
	public String getBlueCommunity() {
		return blueCommunity;
	}

	/**
	 * @param blueCommunity the blueCommunity to set
	 */
	public void setBlueCommunity(String blueCommunity) {
		this.blueCommunity = blueCommunity;
	}

	/**
	 * @return the bam
	 */
	public String getBam() {
		return bam;
	}

	/**
	 * @param bam the bam to set
	 */
	public void setBam(String bam) {
		this.bam = bam;
	}

	/**
	 * @return the projType
	 */
	public String getProjType() {
		return projType;
	}

	/**
	 * @param projType the projType to set
	 */
	public void setProjType(String projType) {
		this.projType = projType;
	}

	/**
	 * @return the projPrimTech
	 */
	public String getProjPrimTech() {
		return projPrimTech;
	}

	/**
	 * @param projPrimTech the projPrimTech to set
	 */
	public void setProjPrimTech(String projPrimTech) {
		this.projPrimTech = projPrimTech;
	}

	/**
	 * @return the domain
	 */
	public String getDomain() {
		return domain;
	}

	/**
	 * @param domain the domain to set
	 */
	public void setDomain(String domain) {
		this.domain = domain;
	}

	/**
	 * @return the deleteFg
	 */
	public String getDeleteFg() {
		return deleteFg;
	}

	/**
	 * @param deleteFg the deleteFg to set
	 */
	public void setDeleteFg(String deleteFg) {
		this.deleteFg = deleteFg;
	}

	/**
	 * @return the objIdHexString
	 */
	public String getObjIdHexString() {
		return objIdHexString;
	}

	/**
	 * @param objIdHexString the objIdHexString to set
	 */
	public void setObjIdHexString(String objIdHexString) {
		this.objIdHexString = objIdHexString;
	}

	/**
	 * @return the projectId
	 */
	public long getProjectId() {
		return projectId;
	}

	/**
	 * @param projectId the projectId to set
	 */
	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}

	/**
	 * @return the displayFileds
	 */
	public Map<String, String> getDisplayFileds() {
		return displayFileds;
	}

	/**
	 * @param displayFileds the displayFileds to set
	 */
	public void setDisplayFileds(Map<String, String> displayFileds) {
		this.displayFileds = displayFileds;
	}

	/**
	 * @return the director
	 */
	public String getDirector() {
		return director;
	}

	/**
	 * @param director the director to set
	 */
	public void setDirector(String director) {
		this.director = director;
	}

	@Override
	public BasicDBObject toDBObject() {
		// TODO Auto-generated method stub
		BasicDBObject dbObject = new BasicDBObject();
		if (this.id == null) {
			this.id = new ObjectId();
		}
		dbObject.put("_id",this.id);
		dbObject.put("uomId",this.uomId);
		dbObject.put("projectName",this.projectName);
		dbObject.put("projMgr",this.projMgr);
		dbObject.put("dpe",this.dpe);
		dbObject.put("blueCommunity",this.blueCommunity);
		dbObject.put("bam",this.bam);
		dbObject.put("director",this.director);
		dbObject.put("projType",this.projType);
		dbObject.put("projPrimTech",this.projPrimTech);
		dbObject.put("domain",this.domain);
		dbObject.put("deleteFg",this.deleteFg);
		dbObject.put("projectId",this.projectId);
		dbObject.put("flm", this.flm);
		dbObject.put("slm", this.slm);
		dbObject.put("l1ex", this.l1ex);
		dbObject.put("l2ex", this.l2ex);
		
		return dbObject;
	}

	public static ProjectDetails parseToObject(DBObject dbObject)
	{
		if(dbObject!=null)
		{
			ProjectDetails parsedObject = new ProjectDetails();
			parsedObject.setId((ObjectId)dbObject.get("_id"));
			if(dbObject.get("uomId") instanceof Integer)
			{
				parsedObject.setUomId(((Integer)dbObject.get("uomId")).longValue());
			}
			else
			{
				parsedObject.setUomId((Long)dbObject.get("uomId"));
			}
			parsedObject.setProjectName((String)dbObject.get("projectName"));
			parsedObject.setProjMgr((String)dbObject.get("projMgr"));
			parsedObject.setDpe((String)dbObject.get("dpe"));
			parsedObject.setBlueCommunity((String)dbObject.get("blueCommunity"));
			parsedObject.setBam((String)dbObject.get("bam"));
			parsedObject.setDirector((String)dbObject.get("director"));
			parsedObject.setProjType((String)dbObject.get("projType"));
			parsedObject.setProjPrimTech((String)dbObject.get("projPrimTech"));
			parsedObject.setDomain((String)dbObject.get("domain"));
			parsedObject.setDeleteFg((String)dbObject.get("deleteFg"));
			if(dbObject.get("projectId") instanceof Integer)
			{
				parsedObject.setProjectId(((Integer)dbObject.get("projectId")).longValue());
			}
			else
			{
				parsedObject.setProjectId((Long)dbObject.get("projectId"));
			}
			parsedObject.setFlm((String)dbObject.get("flm"));
			parsedObject.setSlm((String)dbObject.get("slm"));
			parsedObject.setL1ex((String)dbObject.get("l1ex"));
			parsedObject.setL2ex((String)dbObject.get("l2ex"));
			parsedObject.setObjIdHexString(parsedObject.getId().toHexString());
			return parsedObject;
		}
		return null;
	}

	public void setDisplayFields()
	{
		this.displayFileds = new HashMap<String, String>(6);
		this.displayFileds.put("projType", ReferenceDataHelper.getDisplayName("projectType", this.projType));
		this.displayFileds.put("projPrimTech", ReferenceDataHelper.getDisplayName("projectPrimTech", this.projPrimTech));
		this.displayFileds.put("domain", ReferenceDataHelper.getDisplayName("domainArea", this.domain));
		this.displayFileds.put("projMgr", ManagerReferenceDataHelper.getManagerName("pm",this.projMgr));
		this.displayFileds.put("director", ManagerReferenceDataHelper.getManagerName("dir",this.director));
		this.displayFileds.put("dpe", ManagerReferenceDataHelper.getManagerName("dpe",this.dpe));
		this.displayFileds.put("bam", ManagerReferenceDataHelper.getManagerName("bam",this.bam));
		
		this.displayFileds.put("flm", ManagerReferenceDataHelper.getManagerName("flm",this.flm));
		this.displayFileds.put("slm", ManagerReferenceDataHelper.getManagerName("slm",this.slm));
		this.displayFileds.put("l1ex", ManagerReferenceDataHelper.getManagerName("l1",this.l1ex));
		this.displayFileds.put("l2ex", ManagerReferenceDataHelper.getManagerName("l2",this.l2ex));
		
	}

	/**
	 * @return the uomDetails
	 */
	public UOMDetails getUomDetails() {
		return uomDetails;
	}

	/**
	 * @param uomDetails the uomDetails to set
	 */
	public void setUomDetails(UOMDetails uomDetails) {
		this.uomDetails = uomDetails;
	}
	
	public void updateWith(ProjectDetails updatedObject)
	{
		this.uomId=(updatedObject.uomId!=0?updatedObject.uomId:this.uomId);
		this.projectName=(updatedObject.projectName!=null?updatedObject.projectName:this.projectName);
		this.projMgr=(updatedObject.projMgr!=null?updatedObject.projMgr:this.projMgr);
		this.dpe=(updatedObject.dpe!=null?updatedObject.dpe:this.dpe);
		this.blueCommunity=(updatedObject.blueCommunity!=null?updatedObject.blueCommunity:this.blueCommunity);
		this.bam=(updatedObject.bam!=null?updatedObject.bam:this.bam);
		this.director=(updatedObject.director!=null?updatedObject.director:this.director);
		this.projType=(updatedObject.projType!=null?updatedObject.projType:this.projType);
		this.projPrimTech=(updatedObject.projPrimTech!=null?updatedObject.projPrimTech:this.projPrimTech);
		this.domain=(updatedObject.domain!=null?updatedObject.domain:this.domain);
		this.deleteFg=(updatedObject.deleteFg!=null?updatedObject.deleteFg:this.deleteFg);
		
		this.flm=(updatedObject.flm!=null?updatedObject.flm:this.flm);
		this.slm=(updatedObject.slm!=null?updatedObject.slm:this.slm);
		this.l1ex=(updatedObject.l1ex!=null?updatedObject.l1ex:this.l1ex);
		this.l2ex=(updatedObject.l2ex!=null?updatedObject.l2ex:this.l2ex);
		
		
	}

	/**
	 * @return the flm
	 */
	public String getFlm() {
		return flm;
	}

	/**
	 * @param flm the flm to set
	 */
	public void setFlm(String flm) {
		this.flm = flm;
	}

	/**
	 * @return the slm
	 */
	public String getSlm() {
		return slm;
	}

	/**
	 * @param slm the slm to set
	 */
	public void setSlm(String slm) {
		this.slm = slm;
	}

	/**
	 * @return the l1ex
	 */
	public String getL1ex() {
		return l1ex;
	}

	/**
	 * @param l1ex the l1ex to set
	 */
	public void setL1ex(String l1ex) {
		this.l1ex = l1ex;
	}

	/**
	 * @return the l2ex
	 */
	public String getL2ex() {
		return l2ex;
	}

	/**
	 * @param l2ex the l2ex to set
	 */
	public void setL2ex(String l2ex) {
		this.l2ex = l2ex;
	}
}
