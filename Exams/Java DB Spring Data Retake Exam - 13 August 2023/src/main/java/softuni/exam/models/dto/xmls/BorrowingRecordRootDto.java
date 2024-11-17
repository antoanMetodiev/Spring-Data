package softuni.exam.models.dto.xmls;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "borrowing_records")
@XmlAccessorType(XmlAccessType.FIELD)
public class BorrowingRecordRootDto implements Serializable {

    @XmlElement(name = "borrowing_record")
    private List<BorrowingSeedRecordDto> borrowingSeedRecordDtos;

    public BorrowingRecordRootDto(List<BorrowingSeedRecordDto> borrowingSeedRecordDtos) {
        this.borrowingSeedRecordDtos = borrowingSeedRecordDtos;
    }

    public BorrowingRecordRootDto() {}

    public List<BorrowingSeedRecordDto> getBorrowingSeedRecordDtos() {
        return borrowingSeedRecordDtos;
    }
}
