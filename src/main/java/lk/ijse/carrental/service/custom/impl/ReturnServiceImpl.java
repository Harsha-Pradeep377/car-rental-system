package lk.ijse.carrental.service.custom.impl;

import lk.ijse.carrental.dao.DaoFactory;
import lk.ijse.carrental.dao.DaoType;
import lk.ijse.carrental.dao.custom.BookingDao;
import lk.ijse.carrental.dao.custom.ReturnDao;
import lk.ijse.carrental.dto.ReturnDto;
import lk.ijse.carrental.entity.BookingEntity;
import lk.ijse.carrental.entity.ReturnEntity;
import lk.ijse.carrental.service.custom.ReturnService;

import java.util.List;

public class ReturnServiceImpl implements ReturnService {
    private BookingDao bookingDao = DaoFactory.getDao(DaoType.BOOKING);
    private ReturnDao returnDao = DaoFactory.getDao(DaoType.RETURN);
    @Override
    public void saveBooking(ReturnDto returnDto) {
        BookingEntity bookingEntity = bookingDao.search(returnDto.getBookedId());
        var returnEntity = new ReturnEntity(returnDto.getId(),
                bookingEntity,
                returnDto.getReturnedDate(),
                returnDto.getOverdueDays(),
                returnDto.getOverdueAmount(),
                returnDto.getDamageCost(),
                returnDto.getFinalAmount());
        returnDao.save(returnEntity);
    }

    @Override
    public ReturnDto search(String returnId) {
        return null;
    }

    @Override
    public List<ReturnDto> getAll() {
        return null;
    }
}
