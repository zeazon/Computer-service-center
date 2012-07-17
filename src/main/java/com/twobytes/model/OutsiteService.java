package com.twobytes.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="outsiteService")
public class OutsiteService implements Serializable {

	private static final long serialVersionUID = 7871156654707313607L;
	
	private String outsiteServiceID;
	private Date outsiteServiceDate;
	private ServiceOrder serviceOrder;
	private String refOutsiteJobID;
	private String serviceType;
	private String customerName;
	private String tel;
	private String mobileTel;
	private Type type;
	private Brand brand;
	private Model model;
	private String serialNo;
	private String accessories;
	private String problem;
	private Employee empOpen;
	private OutsiteCompany outsiteCompany;
	private TransportCompany transportCompany;
	private Date sentDate;
	private String sentTransportNo;
	private Date receivedDate;
	private TransportCompany receivedTransportCompany;
	private String receivedTransportNo;
	private String repairing;
	private String costing;
	private Double netAmount;
	private String status;
	private Integer createdBy;
	private Date createdDate;
	private Integer updatedBy;
	private Date updatedDate;
	
	public static String NEW = "new";
	public static String SENT = "sent";
	public static String CANCEL = "cancel";
	public static String RECEIVED = "received";
	public static String CLOSE = "close";
	
	@Id
    @Column(name="outsiteServiceID")    
	public String getOutsiteServiceID() {
		return outsiteServiceID;
	}
	
	public void setOutsiteServiceID(String outsiteServiceID) {
		this.outsiteServiceID = outsiteServiceID;
	}
	
	@Column(name="outsiteServiceDate")
	public Date getOutsiteServiceDate() {
		return outsiteServiceDate;
	}
	
	public void setOutsiteServiceDate(Date outsiteServiceDate) {
		this.outsiteServiceDate = outsiteServiceDate;
	}
	
	@OneToOne
	@JoinColumn(name="serviceOrderID")
	public ServiceOrder getServiceOrder() {
		return serviceOrder;
	}
	
	public void setServiceOrder(ServiceOrder serviceOrder) {
		this.serviceOrder = serviceOrder;
	}
	
	@Column(name="refOutsiteJobID")
	public String getRefOutsiteJobID() {
		return refOutsiteJobID;
	}

	public void setRefOutsiteJobID(String refOutsiteJobID) {
		this.refOutsiteJobID = refOutsiteJobID;
	}

	@Column(name="serviceType")
	public String getServiceType() {
		return serviceType;
	}
	
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	
	@Column(name="customerName")
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	@Column(name="tel")
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Column(name="mobileTel")
	public String getMobileTel() {
		return mobileTel;
	}

	public void setMobileTel(String mobileTel) {
		this.mobileTel = mobileTel;
	}

	@ManyToOne
	@JoinColumn(name="typeID")
	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	@ManyToOne
	@JoinColumn(name="brandID")
	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	@ManyToOne
	@JoinColumn(name="modelID")
	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	@Column(name="serialNo")
	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	@Column(name="accessories")
	public String getAccessories() {
		return accessories;
	}

	public void setAccessories(String accessories) {
		this.accessories = accessories;
	}

	@Column(name="problem")
	public String getProblem() {
		return problem;
	}

	public void setProblem(String problem) {
		this.problem = problem;
	}

	@OneToOne
	@JoinColumn(name="empOpen")
	public Employee getEmpOpen() {
		return empOpen;
	}
	
	public void setEmpOpen(Employee empOpen) {
		this.empOpen = empOpen;
	}
	
	@OneToOne
	@JoinColumn(name="outsiteCompanyID")
	public OutsiteCompany getOutsiteCompany() {
		return outsiteCompany;
	}
	
	public void setOutsiteCompany(OutsiteCompany outsiteCompany) {
		this.outsiteCompany = outsiteCompany;
	}
	
	@ManyToOne
	@JoinColumn(name="transportCompanyID")
	public TransportCompany getTransportCompany() {
		return transportCompany;
	}
	
	public void setTransportCompany(TransportCompany transportCompany) {
		this.transportCompany = transportCompany;
	}
	
	@Column(name="sentDate")
	public Date getSentDate() {
		return sentDate;
	}
	
	public void setSentDate(Date sentDate) {
		this.sentDate = sentDate;
	}
	
	@ManyToOne
	@JoinColumn(name="receivedTransportCompanyID")
	public TransportCompany getReceivedTransportCompany() {
		return receivedTransportCompany;
	}

	public void setReceivedTransportCompany(
			TransportCompany receivedTransportCompany) {
		this.receivedTransportCompany = receivedTransportCompany;
	}

	@Column(name="sentTransportNo")
	public String getSentTransportNo() {
		return sentTransportNo;
	}
	
	public void setSentTransportNo(String sentTransportNo) {
		this.sentTransportNo = sentTransportNo;
	}
	
	@Column(name="receivedDate")
	public Date getReceivedDate() {
		return receivedDate;
	}
	
	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}
	
	@Column(name="receivedTransportNo")
	public String getReceivedTransportNo() {
		return receivedTransportNo;
	}
	
	public void setReceivedTransportNo(String receivedTransportNo) {
		this.receivedTransportNo = receivedTransportNo;
	}
	
	@Column(name="repairing")
	public String getRepairing() {
		return repairing;
	}
	
	public void setRepairing(String repairing) {
		this.repairing = repairing;
	}
	
	@Column(name="costing")
	public String getCosting() {
		return costing;
	}
	
	public void setCosting(String costing) {
		this.costing = costing;
	}
	
	@Column(name="netAmount")
	public Double getNetAmount() {
		return netAmount;
	}
	
	public void setNetAmount(Double netAmount) {
		this.netAmount = netAmount;
	}
	
	@Column(name="status")
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name="createdBy")
	public Integer getCreatedBy() {
		return createdBy;
	}
	
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
	
	@Column(name="createdDate")
	public Date getCreatedDate() {
		return createdDate;
	}
	
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	@Column(name="updatedBy")
	public Integer getUpdatedBy() {
		return updatedBy;
	}
	
	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	@Column(name="updatedDate")
	public Date getUpdatedDate() {
		return updatedDate;
	}
	
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	
}