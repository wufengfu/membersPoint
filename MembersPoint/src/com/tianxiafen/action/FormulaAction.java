package com.tianxiafen.action;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.tianxiafen.entity.Airlinecompany;
import com.tianxiafen.entity.Formula;
import com.tianxiafen.service.IAirlineCompanyService;
import com.tianxiafen.service.IFormulaService;
import com.tianxiafen.service.impl.AirlineCompanyServiceImpl;
import com.tianxiafen.service.impl.FormulaServiceImpl;

public class FormulaAction extends ActionSupport {
	Map<String,Object> session = ActionContext.getContext().getSession();
	private List<Formula> formulas;
	private List<Airlinecompany> companys;
	private int companyId;
	private Formula formula;
	private Airlinecompany company;
	
	private int pageIndex = 1;
	private int pageSize = 5;
	private int itemCount;
	private int pageCount;
	
	
	public String formulaList(){
		if(session.get("manager")==null)
			return LOGIN;
		try {
			IAirlineCompanyService comService = new AirlineCompanyServiceImpl();
			companys = comService.getAllCompany();
			company = comService.getCompanyById(companyId);
			IFormulaService service = new FormulaServiceImpl();
			formulas = service.getFormula(companyId,pageIndex,pageSize);
			itemCount = service.getCount();
			pageCount = (int) (Math.ceil((double)itemCount/pageSize));
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
		
	}
	public String udpateFormula(){
		IFormulaService service = new FormulaServiceImpl();
		service.updateFormula(formula);
		return SUCCESS;
	}
	public String addFormula(){
		IFormulaService service = new FormulaServiceImpl();
		service.addFormula(formula);
		return SUCCESS;
	}
	public List<Formula> getFormulas() {
		return formulas;
	}

	public void setFormulas(List<Formula> formulas) {
		this.formulas = formulas;
	}

	public List<Airlinecompany> getCompanys() {
		return companys;
	}

	public void setCompanys(List<Airlinecompany> companys) {
		this.companys = companys;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public Formula getFormula() {
		return formula;
	}
	public void setFormula(Formula formula) {
		this.formula = formula;
	}
	public Airlinecompany getCompany() {
		return company;
	}
	public void setCompany(Airlinecompany company) {
		this.company = company;
	}
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	
}
