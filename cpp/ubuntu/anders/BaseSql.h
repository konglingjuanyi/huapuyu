#include <iostream>
#include <string>
#include <map>
using namespace std;

#define OTL_ORA9I
#define OTL_STREAM_READ_ITERATOR_ON
#define OTL_STL
#define OTL_ANSI_CPP
#include "otlv4.h"
#include "DataSet.h"
#include "tb_record.h"
#include "config.h"

class BaseSql
{
public:
	int executeUpdate(string sql);
	int executeUpdate(string sql, TB_RECORD model);
	void executeQuery(string sql);
	void executeQuery(string sql, map<int, string> params);
};
