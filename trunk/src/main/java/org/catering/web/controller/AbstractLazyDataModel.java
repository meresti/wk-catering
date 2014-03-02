package org.catering.web.controller;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.catering.ejb.AbstractFacade;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

public abstract class AbstractLazyDataModel<T> extends LazyDataModel<T> {

    private AbstractFacade<T> ejbFacade;

    AbstractLazyDataModel(AbstractFacade<T> facade) {
        ejbFacade = facade;
    }

    @Override
    public List<T> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        final List<T> values;

        if (sortField == null) {
            values = ejbFacade.findRange(first, first + pageSize);
        } else {
            values = ejbFacade.findRange(first, first + pageSize,
                    sortField, sortOrder != SortOrder.DESCENDING);
        }

        setRowCount(ejbFacade.count());

        return values;
    }

    @Override
    public T getRowData(String rowKey) {
        T rowData = null;

        final Collection<T> items = (Collection<T>) getWrappedData();
        final Iterator<T> iterator = items.iterator();
        while (rowData == null && iterator.hasNext()) {
            final T group = iterator.next();
            if (String.valueOf(getRowKey(group)).equals(rowKey)) {
                rowData = group;
            }
        }

        return rowData;
    }
}
