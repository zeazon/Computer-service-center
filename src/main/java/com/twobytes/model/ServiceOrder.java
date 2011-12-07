package com.twobytes.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="serviceOrder")
public class ServiceOrder extends BaseColumn implements Serializable {

	private static final long serialVersionUID = -2341413104107076281L;

	private String serviceOrderID;
	private Date serviceOrderDate;
	private Integer serviceType;
	private String refServiceOrder;
	private String refJobID;
	private Integer guaranteeNo;
	private String customerType;
	private Customer customer = new Customer();
	private String shopCustomerID;
	private String deliveryCustomer;
	private String deliveryEmail;
	private String deliveryTel;
	private String deliveryMobileTel;
	private Product product;
	private String accessories;
	private String description;
	private String problem;
	private Date appointmentDate;
	private Employee empOpen;
	private Employee empFix;
	private Date startFix;
	private Date endFix;
	private String realProblem;
	private String cause;
	private String fixDesc;
	private String costing;
	private Date returnDate;
	private Double totalPrice;
	private String remark;
	private String status;
	
	public static String CUSTOMERTYPE_SHOP="shop";
	public static String CUSTOMERTYPE_WALKIIN="walkin";

	public static String NEW = "new";
	public static String CANCEL = "cancel";
	public static String FIXING = "fixing";
	public static String FIXED = "fixed";
	public static String OUTSITE = "outsite";
	public static String RECEIVED = "received";
	public static String CLOSE = "close";
	
	@Id
    @Column(name="serviceOrderID")
	public String getServiceOrderID() {
		return serviceOrderID;
	}
	
	public void setServiceOrderID(String serviceOrderID) {
		this.serviceOrderID = serviceOrderID;
	}
	
	@Column(name="serviceOrderDate")
	public Date getServiceOrderDate() {
		return serviceOrderDate;
	}
	
	public void setServiceOrderDate(Date serviceOrderDate) {
		this.serviceOrderDate = serviceOrderDate;
	}
	
	@Column(name="serviceType")
	public Integer getServiceType() {
		return serviceType;
	}
	
	public void setServiceType(Integer serviceType) {
		this.serviceType = serviceType;
	}
	
	@Column(name="refServiceOrder")
	public String getRefServiceOrder() {
		return refServiceOrder;
	}
	
	public void setRefServiceOrder(String refServiceOrder) {
		this.refServiceOrder = refServiceOrder;
	}
	
	@Column(name="refJobID")
	public String getRefJobID() {
		return refJobID;
	}

	public void setRefJobID(String refJobID) {
		this.refJobID = refJobID;
	}

	@Column(name="guaranteeNo")
	public Integer getGuaranteeNo() {
		return guaranteeNo;
	}

	public void setGuaranteeNo(Integer guaranteeNo) {
		this.guaranteeNo = guaranteeNo;
	}

	@Column(name="customerType")
	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	@Column(name="shopCustomerID")
	public String getShopCustomerID() {
		return shopCustomerID;
	}

	public void setShopCustomerID(String shopCustomerID) {
		this.shopCustomerID = shopCustomerID;
	}

	@OneToOne
	@JoinColumn(name="customerID")
	public Customer getCustomer() {
		return customer;
	}
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	@Column(name="deliveryCustomer")
	public String getDeliveryCustomer() {
		return deliveryCustomer;
	}

	public void setDeliveryCustomer(String deliveryCustomer) {
		this.deliveryCustomer = deliveryCustomer;
	}

	@Column(name="deliveryEmail")
	public String getDeliveryEmail() {
		return deliveryEmail;
	}

	public void setDeliveryEmail(String deliveryEmail) {
		this.deliveryEmail = deliveryEmail;
	}

	@Column(name="deliveryTel")
	public String getDeliveryTel() {
		return deliveryTel;
	}

	public void setDeliveryTel(String deliveryTel) {
		this.deliveryTel = deliveryTel;
	}

	@Column(name="deliveryMobileTel")
	public String getDeliveryMobileTel() {
		return deliveryMobileTel;
	}

	public void setDeliveryMobileTel(String deliveryMobileTel) {
		this.deliveryMobileTel = deliveryMobileTel;
	}

	@OneToOne
	@JoinColumn(name="productID")
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Column(name="accessories")
	public String getAccessories() {
		return accessories;
	}
	
	public void setAccessories(String accessories) {
		this.accessories = accessories;
	}
	
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name="problem")
	public String getProblem() {
		return problem;
	}
	
	public void setProblem(String problem) {
		this.problem = problem;
	}
	
	@Column(name="appointmentDate")
	public Date getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
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
	@JoinColumn(name="empFix")
	public Employee getEmpFix() {
		return empFix;
	}
	
	public void setEmpFix(Employee empFix) {
		this.empFix = empFix;
	}
	
	@Column(name="startFix")
	public Date getStartFix() {
		return startFix;
	}
	
	public void setStartFix(Date startFix) {
		this.startFix = startFix;
	}
	
	@Column(name="endFix")
	public Date getEndFix() {
		return endFix;
	}
	
	public void setEndFix(Date endFix) {
		this.endFix = endFix;
	}
	
	@Column(name="realProblem")
	public String getRealProblem() {
		return realProblem;
	}

	public void setRealProblem(String realProblem) {
		this.realProblem = realProblem;
	}
	
	@Column(name="cause")
	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	@Column(name="fixDesc")
	public String getFixDesc() {
		return fixDesc;
	}
	
	public void setFixDesc(String fixDesc) {
		this.fixDesc = fixDesc;
	}
	
	@Column(name="costing")
	public String getCosting() {
		return costing;
	}
	
	public void setCosting(String costing) {
		this.costing = costing;
	}
	
	@Column(name="returnDate")
	public Date getReturnDate() {
		return returnDate;
	}
	
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	
	@Column(name="totalPrice")
	public Double getTotalPrice() {
		return totalPrice;
	}
	
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	@Column(name="remark")
	public String getRemark() {
		return remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Column(name="status")
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
}
