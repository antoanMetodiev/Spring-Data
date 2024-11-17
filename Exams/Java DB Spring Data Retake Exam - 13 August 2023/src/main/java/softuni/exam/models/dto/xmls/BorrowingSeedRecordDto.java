package softuni.exam.models.dto.xmls;


import softuni.exam.models.dto.jsons.BookDto;
import softuni.exam.models.dto.jsons.LibraryMemberDto;
import softuni.exam.models.entity.Book;
import softuni.exam.models.entity.LibraryMember;
import softuni.exam.util.XmlLocalDateAdapter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;

@XmlRootElement(name = "borrowing_record")
@XmlAccessorType(XmlAccessType.FIELD)
public class BorrowingSeedRecordDto implements Serializable {

    @XmlElement(name = "borrow_date")
    @XmlJavaTypeAdapter(XmlLocalDateAdapter.class)
    @NotNull
    private LocalDate borrowDate;

    @XmlElement(name = "return_date")
    @XmlJavaTypeAdapter(XmlLocalDateAdapter.class)
    @NotNull
    private LocalDate returnDate;

    @XmlElement
    @NotNull
    private BookDto book;

    @XmlElement(name = "member")
    @NotNull
    private LibraryMemberXmlSeedDTO member;

    @XmlElement
    @Size(min = 3, max = 100)
    private String remarks;


    public BorrowingSeedRecordDto(LocalDate borrowDate, LocalDate returnDate, BookDto book, LibraryMemberXmlSeedDTO member, String remarks) {
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.book = book;
        this.member = member;
        this.remarks = remarks;
    }

    public BorrowingSeedRecordDto() {}

    public @NotNull LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(@NotNull LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public @NotNull LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(@NotNull LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public @NotNull BookDto getBook() {
        return book;
    }

    public void setBook(@NotNull BookDto book) {
        this.book = book;
    }

    public @NotNull LibraryMemberXmlSeedDTO getMember() {
        return member;
    }

    public void setMember(@NotNull LibraryMemberXmlSeedDTO member) {
        this.member = member;
    }

    public @Size(min = 3, max = 100) String getRemarks() {
        return remarks;
    }

    public void setRemarks(@Size(min = 3, max = 100) String remarks) {
        this.remarks = remarks;
    }
}
