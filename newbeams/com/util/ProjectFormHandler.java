package com.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.*;
import java.sql.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Hashtable;
import java.util.Vector;
import java.util.ArrayList;

public class ProjectFormHandler extends GenericFormHandler{
    SQLHandler sqlHandler = new SQLHandler();
    Project project = new Project();
    Project newProject;
    HashMap projects = new HashMap();
    Vector projectDetail = new Vector();
    String userName;
    boolean isNewProject = false;
    boolean beamInserted = false;

    public ProjectFormHandler(){
        //loggingDebug = false;
    }

    public void setLastProject(String pUserName){
        try{
            sqlHandler.getLastProject(pUserName , project);
        }catch(Exception e){ e.printStackTrace(); }
    }

    public void setUserName(String pUserName){
        userName = pUserName;
        projects.clear();
        project = new Project();
    }
    public String getUserName(){ return userName; }

    public void setProject(Project pProject){ project = pProject; }
    public Project getProject() { return project; }

    public void createNewProject(){ newProject = new Project(); }

    public void setNewProject(Project pNewProject){ newProject = pNewProject; }
    public Project getNewProject() { return newProject; }

    public void setIsNewProject(boolean pIsNewProject) { isNewProject = pIsNewProject; }
    public boolean getIsNewProject() { return isNewProject; }

    public void setBeamInserted(boolean pBeamInserted) { beamInserted = pBeamInserted; }
    public boolean getBeamInserted() { return beamInserted; }

    public void setProjects(){
        try{
            projects = sqlHandler.getProjects(userName);
            if ((project.getProjectName() == null) || (project.getProjectName().length() <1)) {
                Iterator _projects = projects.keySet().iterator();
                while (_projects.hasNext()){
                    project = getProjectFromTable((String)_projects.next());
                    return;
                }
            }
        }catch(Exception e){
            setLogError(e.getMessage());
        }
    }
    public boolean hasProjects(){
        if (projects.size() > 0) { return true;
        }else return false;
    }
    public Iterator getProjects(){ return projects.keySet().iterator(); }
    public Project getProjectFromTable(String pProjectName) { return (Project)projects.get(pProjectName); }

    // Retrive all beams detail for pProjectName and present userName
    public void setProjectDetail(String pProjectName){
        try{
            projectDetail.clear();
            if ((pProjectName != null) && (pProjectName.length() > 0)) {
                projectDetail = sqlHandler.getProjectDetail(userName,pProjectName);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public Vector getProjectDetail() { return projectDetail; }

    public void insertProject(){
        if (isNewProject) {
            try{
                Iterator _projects = getProjects();
                while (_projects.hasNext()) {
                    if (project.getProjectName().equals((String)_projects.next())){
                        isNewProject = false;
                        break;
                    }
                }
                System.out.println("Project insert:"+ project.getProjectName() +"-end");
                if (isNewProject) {
                    isNewProject = false;
                    sqlHandler.insertProject(project,userName);
                }
            }catch (Exception e){ e.printStackTrace(); }
        }
    }

    public void insertDesignBeam(Design pDesign, String pDescription, double pDeflection){
        try{
            if (!(beamInserted)) {
                sqlHandler.insertBeam(pDesign, project.getProjectName(), userName, pDescription, pDeflection);
                beamInserted = true;
            }
        }catch (Exception e){ e.printStackTrace(); }
    }

    public void insertAFrame(Design pDesign, String pDescription){
        try{
            if (!(beamInserted)) {
                sqlHandler.insertAFrame(pDesign, project.getProjectName(), userName, pDescription);
                beamInserted = true;
            }
        }catch (Exception e){ e.printStackTrace(); }
    }
    boolean validateParams(Project pProject){
        if (pProject.getProjectName().length() < 1) {
            addFormException(Messages.INVALID_PROJECTNAME);
            return false;
        }
        return true;
    }
    public boolean processMethod(HttpServletRequest pReq , HttpServletResponse pRes) throws Exception{
        return true;
    }

    public boolean ProcessInsertNewProject(HttpServletRequest pReq , HttpServletResponse pRes) throws Exception{
        if (!validateParams(newProject)){
            System.out.println("Invalid Param from ProcessInsertNewProject");
            return false;
        }
        if (isNewProject) {
            try{
                Iterator _projects = getProjects();
                while (_projects.hasNext()) {
                    if (newProject.getProjectName().equals((String)_projects.next())){
                        isNewProject = false;
                        break;
                    }
                }
                System.out.println("Project insert:"+ newProject.getProjectName() +"-end");
                if (isNewProject) {
                    isNewProject = false;
                    sqlHandler.insertProject(newProject,userName);
                    projects.put(newProject.getProjectName(), newProject);
                }else{
                    addFormException(Messages.PROJECTNAME_EXIST);
                    return false;
                }
            }catch (Exception e){ e.printStackTrace(); }
        }
        return true;
    }

    public boolean processUpdateProject(HttpServletRequest pReq , HttpServletResponse pRes) throws Exception{
        if (!validateParams(project)){
            System.out.println("Invalid Param");
            return false;
        }
        try{
            sqlHandler.updateProject(project, userName);
            projects.remove(project.getProjectName());
            projects.put(project.getProjectName(), project);
        }catch(Exception e){
            e.printStackTrace();
        }
        return true;
    }
}
