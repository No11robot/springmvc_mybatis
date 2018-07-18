package ldz.ssm.po;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

public class Items {
    @Override
	public String toString() {
		return "Items [id=" + id + ", name=" + name + ", price=" + price + ", pic=" + pic + ", createtime=" + createtime
				+ ", detail=" + detail + "]";
	}

	private Integer id;
	//校验名称在1到10字符中间
    //message是提示校验出错显示的信息
    //groups：此校验属于哪个分组，groups可以定义多个分组
    @Size(min=1,max=10,message="{items.name.length.error}")
    private String name;

    private BigDecimal price;

    private String pic;
    //@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
  //非空校验
    @NotNull(message="{items.createtime.isNUll}")
    private Date createtime;

    private String detail;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }
}