#ifndef CPP_TASK4_SYNONYMTABLETOSTANDARDITEMMODELCONVERTER_H
#define CPP_TASK4_SYNONYMTABLETOSTANDARDITEMMODELCONVERTER_H


#include <QStandardItemModel>
#include "SynonymTable.h"

class SynonymTableToStandardItemModelConverter {
public:
    SynonymTableToStandardItemModelConverter(QStandardItemModel *model, SynonymTable *synonyms);
    void convert();
private:
    QStandardItemModel *model;
    SynonymTable *synonyms;
};


#endif
