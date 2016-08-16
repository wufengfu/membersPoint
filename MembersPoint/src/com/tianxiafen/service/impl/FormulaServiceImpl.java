package com.tianxiafen.service.impl;

import java.util.List;

import com.tianxiafen.dao.IFormulaDao;
import com.tianxiafen.dao.impl.FormulaDaoImpl;
import com.tianxiafen.entity.Formula;
import com.tianxiafen.service.IFormulaService;

public class FormulaServiceImpl implements IFormulaService {
	
	IFormulaDao dao = new FormulaDaoImpl();
	@Override
	public List<Formula> getFormula(int companyId,int pageIndex,int pageSize) {
		return dao.getFormula(companyId,pageIndex,pageSize);
	}

	@Override
	public Formula addFormula(Formula formula) {
		return dao.addFormula(formula);
	}

	@Override
	public boolean deleteFormula(int formulaId) {
		return dao.deleteFormula(formulaId);
	}

	@Override
	public Formula updateFormula(Formula formula) {
		return dao.updateFormula(formula);
	}

	@Override
	public int getCount() {
		return dao.getCount();
	}

}
