package com.app.demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;

import com.app.demo.fluttercode.datafactory.impl.DataFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.demo.dao.AssetDAO;
import com.app.demo.dao.CustomerDAO;
import com.app.demo.dao.PortfolioDAO;
import com.app.demo.model.Asset;
import com.app.demo.model.Customer;
import com.app.demo.model.Portfolio;
import com.app.demo.util.DBUtils;

@Component
public class DataThread {

	@Autowired
	AssetDAO assetDAO;

	@Autowired
	CustomerDAO customerDAO;

	@Autowired
	PortfolioDAO portfolioDAO;

	String[] STOCK = { "APPL", "GOOGL", "MOTO", "YAHO", "REDF", "SNY", "DELL", "SMSNG", "ORCL", "MTOR", "ABB", "AEE",
			"IX", "MRT", "MPW", "MED", "MCC", "MCV", "MCX", "MDLX", "OHI", "OMC", "OMN", "OOMA", "OPY", "ORCL", "ORAN",
			"OA", "OEC", "ORN", "JMM", "NHA", "NXJ", "NNY", "NAN", "NNC", "ODC", "ORI", "OMAA", "JLS", "AFB", "JHY",
			"JHD", "JHB", "NXC", "NXN", "NID", "NUM", "NMS", "NOM", "AEG", "PGEM", "JCE", "JCO", "JQC", "JDD", "JFR",
			"NKG", "JGH", "JHA", "ADT", "ADNT", "POST", "NAT", "JWN", "NOA", "NWE", "NCLH", "NAC", "NTC", "ANET", "PES",
			"MAV", "MHI", "PXD", "PJC", "PAA", "PLNT", "PLT", "PAH", "AMN", "PTY", "PCN", "PCI", "PFL", "PFN", "PMF",
			"PML", "PMX", "PNF", "PM", "FENG", "DOC", "PDM", "PIR", "PCK", "PGTI", "PHH", "PBR", "SEND", "RSO", "QSR",
			"REV", "REVG", "RXN", "RH", "RBA", "RAD", "RIO", "SSL", "AOD", "COO", "RUBI", "THR", "TMO", "TCRZ", "TIER",
			"TIF", "TSU", "TKR", "AZUL", "GIM", "THC", "TNC", "TEN", "TEX", "TTI", "TEVA", "TPL", "TX", "ABT", "ANF",
			"ABBV", "AAN", "AAP", "ASX", "ASIX", "TEF", "TDA", "TDE", "LCM", "ACM", "AVK", "HIVE", "MITT", "AGCO",
			"AFL", "NIE", "NCZ", "DDD", "BABA", "ALE", "AOI", "LNT", "CBH", "NCV", "ADS", "ALL", "ALSN", "NFJ", "MMM",
			"WUBA", "ATEN", "AAC", "COLD", "AMN", "APU", "AWK", "WAGE", "HCC", "HCC", "WPG", "WBC", "STAG", "SSI",
			"SMP", "SXI", "SWK", "SWP", "", "STO", "SPLP", "SCS", "SCA", "STL", "SF", "SFB", "EDI", "STOR", "STON",
			"SUI", "SMLP", "SUM", "GJO", "SMFG", "INN", "SYK", "EDF", "GJO", "SRI", "SUN", "SHO", "TPR", "TGT", "TCO",
			"TTM", "TOO", "GCI", "TGNA", "TRC", };
	String[] STOCK_TYPE = { "stock", "bond" };

	DataFactory df = new DataFactory();

	public DataThread() {
	}

	/**
	 * populate Asset
	 *
	 * 
	 */
	public void populateAsset() {
		long start = System.currentTimeMillis();
		for (int i = 1; i <= DBUtils.numberOfAsset; i++) {
			String name = "Asset_" + df.getItem(STOCK) + "_" + i;
			String stock = df.getItem(STOCK_TYPE);
			Asset asset = new Asset(name, stock);
			assetDAO.create(asset);
		}
		long time = System.currentTimeMillis() - start;
		printMsg("Asset", DBUtils.numberOfAsset, time);
	}

	/**
	 * populate Customer
	 */
	public void populateCustomer() {
		long start = System.currentTimeMillis();
		for (int i = 1; i <= DBUtils.numberOfCustomer; i++) {
			String name = "C_" + df.getFirstName() + "_" + i;
			Customer customer = new Customer(name);
			customerDAO.create(customer);
		}
		long time = System.currentTimeMillis() - start;

		printMsg("Customer", DBUtils.numberOfCustomer, time);

	}

	public void printMsg(String table, int rows, long time) {
		System.out.println(rows + " inserts in " + table + " took: " + time + "ms");

	}

	/**
	 * populate portfolio
	 */
	public void populatePortfolio() {
		long start = System.currentTimeMillis();
		for (int i = 1; i <= DBUtils.numberOfCustomer; i++) {
			int customerId = i;
			int assetId = df.getNumberBetween(1, DBUtils.numberOfAsset);
			String portfolioName = "P_" + df.getLastName() + "_" + i;
			Portfolio portfolio = new Portfolio(portfolioName, customerId, assetId);
			portfolioDAO.create(portfolio);
		}
		long time = System.currentTimeMillis() - start;
		printMsg("Portfolio", DBUtils.numberOfCustomer, time);
	}

	public void run() {
		populateAsset();
		populateCustomer();
		populatePortfolio();
	}
}