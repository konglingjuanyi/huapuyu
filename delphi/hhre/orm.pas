unit orm;

interface

uses
  Rtti, SysUtils, TypInfo, stringBuilder, sql, DB, Forms, Dialogs, constant, Generics.Collections, Variants;

const
  DEFAULT_VALUE = -99;

type
  TTableInfo = class(TCustomAttribute)
  strict private
    AName: string;
    ATitle: string;
  public
    constructor Create(const name, title: string); overload;
    constructor Create(const name: string); overload;
    property Name: string read AName write AName;
    property Title: string read ATitle write ATitle;
  end;

  TFieldInfo = class(TCustomAttribute)
  strict private
    AName: string;
    ATitle: string;
  public
    constructor Create(const name, title: string); overload;
    constructor Create(const name: string); overload;
    property Name: string read AName write AName;
    property Title: string read ATitle write ATitle;
  end;

  TModel = class
  public
    constructor Create;
    function Insert: string;
    function InsertWithoutId: string;
    function InsertWithSql: string;
    function Update: string;
    function UpdateWithSql: string;
    function DeleteById: string;
    function DeleteByIdWithSql: string;
    procedure MappingData(const id: Integer);
//    function Select: string;
//    function SelectWithSql: string;
    function GetFieldTitle(const fieldName: string): string;
    function ToString: string; override;
  end;

implementation

constructor TTableInfo.Create(const name, title: string);
begin
  Self.AName := name;
  Self.ATitle := title;
end;

constructor TTableInfo.Create(const name: string);
begin
  Create(name, EmptyStr);
end;

constructor TFieldInfo.Create(const name, title: string);
begin
  Self.AName := name;
  Self.ATitle := title;
end;

constructor TFieldInfo.Create(const name: string);
begin
  Create(name, EmptyStr);
end;

constructor TModel.Create;
var
  context: TRttiContext;
  prop: TRttiProperty;
  typ: TRttiType;
  tableAttr, fieldAttr: TCustomAttribute;
begin
  context := TRttiContext.Create;
  try
    typ := context.GetType(ClassType);
    for tableAttr in typ.GetAttributes do
    begin
      if tableAttr is TTableInfo then
      begin
        for prop in typ.GetProperties do
        begin
          for fieldAttr in prop.GetAttributes do
          begin
            if fieldAttr is TFieldInfo then
            begin
              case prop.GetValue(Self).Kind of
                tkInteger, tkInt64, tkFloat:
                  prop.SetValue(Self, DEFAULT_VALUE);
              else
                prop.SetValue(Self, EmptyStr);
              end;
            end;
          end;
        end;
      end;
    end;
  finally
    context.Free;
  end;
end;

function TModel.DeleteById: string;
var
  context: TRttiContext;
  prop: TRttiProperty;
  typ: TRttiType;
  tableAttr, fieldAttr: TCustomAttribute;
  whereExp, pkValue, table, sql: string;
  params: TParams;
  param: TParam;
begin
  context := TRttiContext.Create;
  params := TParams.Create(Application);
  whereExp := EmptyStr;
  pkValue := EmptyStr;
  table := EmptyStr;
  sql := EmptyStr;
  try
    typ := context.GetType(ClassType);
    for tableAttr in typ.GetAttributes do
    begin
      if tableAttr is TTableInfo then
      begin
        table := (tableAttr as TTableInfo).Name;
        for prop in typ.GetProperties do
        begin
          for fieldAttr in prop.GetAttributes do
          begin
            if (fieldAttr is TFieldInfo) and SameStr((fieldAttr as TFieldInfo).Name, PRIMARY_KEY_NAME) then
            begin
              whereExp := (fieldAttr as TFieldInfo).Name + '=:' + (fieldAttr as TFieldInfo).Name;
              pkValue := prop.GetValue(Self).ToString;
            end;
          end;
        end;

        param := params.CreateParam(ftInteger, ':' + PRIMARY_KEY_NAME, ptInput);
        param.Value := pkValue;

        sql := Format(SQL_DELETE_TEMPLATE, [table, whereExp]);

        TSql.ExecuteSql(sql, params);

        Result := sql;
      end;
    end;
  finally
    context.Free;
  end;
end;

function TModel.DeleteByIdWithSql: string;
var
  context: TRttiContext;
  prop: TRttiProperty;
  typ: TRttiType;
  tableAttr, fieldAttr: TCustomAttribute;
  whereExp, table, sql: string;
  params: TParams;
  param: TParam;
begin
  context := TRttiContext.Create;
  whereExp := EmptyStr;
  table := EmptyStr;
  sql := EmptyStr;
  try
    typ := context.GetType(ClassType);
    for tableAttr in typ.GetAttributes do
    begin
      if tableAttr is TTableInfo then
      begin
        table := (tableAttr as TTableInfo).Name;
        for prop in typ.GetProperties do
        begin
          for fieldAttr in prop.GetAttributes do
          begin
            if (fieldAttr is TFieldInfo) and SameStr((fieldAttr as TFieldInfo).Name, PRIMARY_KEY_NAME) then
              whereExp := (fieldAttr as TFieldInfo).Name + '=' + prop.GetValue(Self).ToString;
          end;
        end;

        sql := Format(SQL_DELETE_TEMPLATE, [table, whereExp]);

        TSql.ExecuteSql(sql);

        Result := sql;
      end;
    end;
  finally
    context.Free;
  end;
end;

function TModel.GetFieldTitle(const fieldName: string): string;
var
  context: TRttiContext;
  typ: TRttiType;
  attr: TCustomAttribute;
  prop: TRttiProperty;
begin
  context := TRttiContext.Create;
  try
    typ := context.GetType(ClassType);
    for prop in typ.GetProperties do
    begin
      for attr in prop.GetAttributes do
      begin
        if (attr is TFieldInfo) and SameText((attr as TFieldInfo).Name, fieldName) then
        begin
          Result := (attr as TFieldInfo).title;
          Break;
        end;
      end;
    end;
  finally
    context.Free;
  end;
end;

function TModel.InsertWithoutId: string;
var
  context: TRttiContext;
  prop: TRttiProperty;
  typ: TRttiType;
  tableAttr, fieldAttr: TCustomAttribute;
  fields, values, value, table, sql: string;
  params: TParams;
  param: TParam;
begin
  context := TRttiContext.Create;
  fields := EmptyStr;
  values := EmptyStr;
  value := EmptyStr;
  table := EmptyStr;
  sql := EmptyStr;
  params := TParams.Create(Application);
  try
    typ := context.GetType(ClassType);
    for tableAttr in typ.GetAttributes do
    begin
      if tableAttr is TTableInfo then
      begin
        table := (tableAttr as TTableInfo).Name;
        for prop in typ.GetProperties do
        begin
          for fieldAttr in prop.GetAttributes do
          begin
            if fieldAttr is TFieldInfo then
            begin
              case prop.GetValue(Self).Kind of
                tkString, tkChar, tkWChar, tkWString, tkUString, tkLString:
                begin
                  if SameStr(prop.GetValue(Self).AsString, EmptyStr) then
                    Continue;
                  param := params.CreateParam(ftString, ':' + (fieldAttr as TFieldInfo).Name, ptInput);
                end;
                tkInteger, tkInt64:
                begin
                  if prop.GetValue(Self).AsInteger = DEFAULT_VALUE then
                    Continue;
                  param := params.CreateParam(ftInteger, ':' + (fieldAttr as TFieldInfo).Name, ptInput);
                end;
                tkFloat:
                begin
                  if StrToFloat(prop.GetValue(Self).ToString) = DEFAULT_VALUE then
                    Continue;
                  param := params.CreateParam(ftFloat, ':' + (fieldAttr as TFieldInfo).Name, ptInput);
                end
              else
                param := params.CreateParam(ftString, ':' + (fieldAttr as TFieldInfo).Name, ptInput);
              end;

              fields := fields + ',' + (fieldAttr as TFieldInfo).Name;
              values := values + ',:' + (fieldAttr as TFieldInfo).Name;
              value := prop.GetValue(Self).ToString;
              param.Value := value;
            end;
          end;
        end;

        Delete(fields, 1, 1);
        Delete(values, 1, 1);

        sql := Format(SQL_INSERT_TEMPLATE, [table, fields, values]);

        TSql.ExecuteSql(sql, params);

        Result := sql;
      end;
    end;
  finally
    context.Free;
  end;
end;

function TModel.InsertWithSql: string;
var
  context: TRttiContext;
  prop: TRttiProperty;
  typ: TRttiType;
  tableAttr, fieldAttr: TCustomAttribute;
  fields, values, value, table, sql: string;
begin
  context := TRttiContext.Create;
  fields := EmptyStr;
  values := EmptyStr;
  value := EmptyStr;
  table := EmptyStr;
  sql := EmptyStr;
  try
    typ := context.GetType(ClassType);
    for tableAttr in typ.GetAttributes do
    begin
      if tableAttr is TTableInfo then
      begin
        table := (tableAttr as TTableInfo).Name;
        for prop in typ.GetProperties do
        begin
          for fieldAttr in prop.GetAttributes do
          begin
            if (fieldAttr is TFieldInfo) and not SameStr((fieldAttr as TFieldInfo).Name, PRIMARY_KEY_NAME) then
            begin
              fields := fields + ',' + (fieldAttr as TFieldInfo).Name;
              value := prop.GetValue(Self).ToString;
              case prop.GetValue(Self).Kind of
                tkString, tkChar, tkWChar, tkWString, tkUString, tkLString:
                  value := QuotedStr(value);
                tkInteger, tkInt64, tkFloat:
                  value := value;
              else
                value := QuotedStr(value);
              end;
              values := values + ',' + value;
            end;
          end;
        end;

        Delete(fields, 1, 1);
        Delete(values, 1, 1);

        sql := Format(SQL_INSERT_TEMPLATE, [table, fields, values]);

        TSql.ExecuteSql(sql);

        Result := sql;
      end;
    end;
  finally
    context.Free;
  end;
end;

procedure TModel.MappingData(const id: Integer);
var
  context: TRttiContext;
  prop: TRttiProperty;
  typ: TRttiType;
  tableAttr, fieldAttr: TCustomAttribute;
  table: string;
  map: TDictionary<string, Variant>;
begin
  context := TRttiContext.Create;
  table := EmptyStr;
  try
    typ := context.GetType(ClassType);
    for tableAttr in typ.GetAttributes do
    begin
      if tableAttr is TTableInfo then
      begin
        table := (tableAttr as TTableInfo).Name;
        map := TSql.ExecuteQueryRow(Format(SQL_SELECT_BY_ID_TEMPLATE, [table, IntToStr(id)]));

        for prop in typ.GetProperties do
        begin
          for fieldAttr in prop.GetAttributes do
          begin
            if fieldAttr is TFieldInfo then
            begin
              case prop.GetValue(Self).Kind of
                tkString, tkChar, tkWChar, tkWString, tkUString, tkLString:
                begin
                  try
                    prop.SetValue(Self, VarToStr(map.Items[(fieldAttr as TFieldInfo).Name]));
                  except
                    Continue;
                  end;
                end;
                tkInteger, tkInt64:
                begin
                  try
                    prop.SetValue(Self, StrToInt(VarToStr(map.Items[(fieldAttr as TFieldInfo).Name])));
                  except
                    Continue;
                  end;
                end;
                tkFloat:
                begin
                  try
                    prop.SetValue(Self, StrToFloat(VarToStr(map.Items[(fieldAttr as TFieldInfo).Name])));
                  except
                    Continue;
                  end;
                end
              else
                try
                  prop.SetValue(Self, VarToStr(map.Items[(fieldAttr as TFieldInfo).Name]));
                except
                  Continue;
                end;
              end;
            end;
          end;
        end;
      end;
    end;
  finally
    context.Free;
  end;
end;

function TModel.ToString: string;
var
  context: TRttiContext;
  prop: TRttiProperty;
  typ: TRttiType;
  tableAttr, fieldAttr: TCustomAttribute;
  sb: TStringBuilder;
begin
  context := TRttiContext.Create;
  sb := TStringBuilder.Create;
  try
    typ := context.GetType(ClassType);
    for tableAttr in typ.GetAttributes do
    begin
      if tableAttr is TTableInfo then
      begin
        for prop in typ.GetProperties do
        begin
          for fieldAttr in prop.GetAttributes do
          begin
            if fieldAttr is TFieldInfo then
            begin
              sb.Append((fieldAttr as TFieldInfo).Name + ':' + prop.GetValue(Self).ToString + #13#10);
            end;
          end;
        end;

        Result := sb.ToString;
      end;
    end;
  finally
    context.Free;
  end;
end;

//function TModel.Select: string;
//var
//  context: TRttiContext;
//  prop: TRttiProperty;
//  typ: TRttiType;
//  tableAttr, fieldAttr: TCustomAttribute;
//  fields, values, table, sql: string;
//  params: TParams;
//  param: TParam;
//begin
//  context := TRttiContext.Create;
//  fields := EmptyStr;
//  values := EmptyStr;
//  table := EmptyStr;
//  sql := EmptyStr;
//  params := TParams.Create(Application);
//  try
//    typ := context.GetType(ClassType);
//    for tableAttr in typ.GetAttributes do
//    begin
//      if tableAttr is TTableInfo then
//      begin
//        table := (tableAttr as TTableInfo).Name;
//        for prop in typ.GetProperties do
//        begin
//          for fieldAttr in prop.GetAttributes do
//          begin
//            if fieldAttr is TFieldInfo then
//            begin
//              case prop.GetValue(Self).Kind of
//                tkString, tkChar, tkWChar, tkWString, tkUString, tkLString:
//                begin
//                  if SameStr(prop.GetValue.AsString, EmptyStr) then
//                    param := params.CreateParam(ftString, ':' + (fieldAttr as TFieldInfo).Name, ptInput);
//                end;
//                tkInteger, tkInt64:
//                  param := params.CreateParam(ftInteger, ':' + (fieldAttr as TFieldInfo).Name, ptInput);
//                tkFloat:
//                  param := params.CreateParam(ftFloat, ':' + (fieldAttr as TFieldInfo).Name, ptInput);
//              else
//                param := params.CreateParam(ftString, ':' + (fieldAttr as TFieldInfo).Name, ptInput);
//              end;
//
//
//              fields := fields + ',' + (fieldAttr as TFieldInfo).Name;
//              values := values + ',:' + (fieldAttr as TFieldInfo).Name;
//              value := prop.GetValue(Self).ToString;
//
//
//              param.Value := value;
//            end;
//          end;
//        end;
//
//        Delete(fields, 1, 1);
//        Delete(values, 1, 1);
//
//        sql := Format(INSERT_TEMPLATE, [table, fields, values]);
//
//        TSql.ExecuteSql(sql, params);
//
//        Result := sql;
//      end;
//    end;
//  finally
//    context.Free;
//  end;
//end;
//
//function TModel.SelectWithSql: string;
//begin
//
//end;

function TModel.Insert: string;
var
  context: TRttiContext;
  prop: TRttiProperty;
  typ: TRttiType;
  tableAttr, fieldAttr: TCustomAttribute;
  fields, values, value, table, sql: string;
  params: TParams;
  param: TParam;
  id: Integer;
begin
  context := TRttiContext.Create;
  fields := EmptyStr;
  values := EmptyStr;
  value := EmptyStr;
  table := EmptyStr;
  sql := EmptyStr;
  params := TParams.Create(Application);
  try
    typ := context.GetType(ClassType);
    for tableAttr in typ.GetAttributes do
    begin
      if tableAttr is TTableInfo then
      begin
        table := (tableAttr as TTableInfo).Name;
        for prop in typ.GetProperties do
        begin
          for fieldAttr in prop.GetAttributes do
          begin
            if (fieldAttr is TFieldInfo) and not SameStr((fieldAttr as TFieldInfo).Name, PRIMARY_KEY_NAME) then
            begin
              case prop.GetValue(Self).Kind of
                tkString, tkChar, tkWChar, tkWString, tkUString, tkLString:
                begin
                  if SameStr(prop.GetValue(Self).AsString, EmptyStr) then
                    Continue;
                  param := params.CreateParam(ftString, ':' + (fieldAttr as TFieldInfo).Name, ptInput);
                end;
                tkInteger, tkInt64:
                begin
                  if prop.GetValue(Self).AsInteger = DEFAULT_VALUE then
                    Continue;
                  param := params.CreateParam(ftInteger, ':' + (fieldAttr as TFieldInfo).Name, ptInput);
                end;
                tkFloat:
                begin
                  if StrToFloat(prop.GetValue(Self).ToString) = DEFAULT_VALUE then
                    Continue;
                  param := params.CreateParam(ftFloat, ':' + (fieldAttr as TFieldInfo).Name, ptInput);
                end
              else
                param := params.CreateParam(ftString, ':' + (fieldAttr as TFieldInfo).Name, ptInput);
              end;

              fields := fields + ',' + (fieldAttr as TFieldInfo).Name;
              values := values + ',:' + (fieldAttr as TFieldInfo).Name;
              value := prop.GetValue(Self).ToString;
              param.Value := value;
            end;
          end;
        end;

        Delete(fields, 1, 1);
        Delete(values, 1, 1);

        sql := Format(SQL_INSERT_TEMPLATE, [table, fields, values]);

        id := TSql.ExecuteInsert(sql, params);

        for prop in typ.GetProperties do
        begin
          for fieldAttr in prop.GetAttributes do
          begin
            if (fieldAttr is TFieldInfo) and SameStr((fieldAttr as TFieldInfo).Name, PRIMARY_KEY_NAME) then
              prop.SetValue(Self, id);
          end;
        end;

        Result := sql;
      end;
    end;
  finally
    context.Free;
  end;
end;

function TModel.UpdateWithSql: string;
var
  context: TRttiContext;
  prop: TRttiProperty;
  typ: TRttiType;
  tableAttr, fieldAttr: TCustomAttribute;
  setExp, setExps, whereExp, value, table, sql: string;
begin
  context := TRttiContext.Create;
  setExp := EmptyStr;
  setExps := EmptyStr;
  whereExp := EmptyStr;
  value := EmptyStr;
  table := EmptyStr;
  sql := EmptyStr;
  try
    typ := context.GetType(ClassType);
    for tableAttr in typ.GetAttributes do
    begin
      if tableAttr is TTableInfo then
      begin
        table := (tableAttr as TTableInfo).Name;
        for prop in typ.GetProperties do
        begin
          for fieldAttr in prop.GetAttributes do
          begin
            if (fieldAttr is TFieldInfo) then
              if not SameStr((fieldAttr as TFieldInfo).Name, PRIMARY_KEY_NAME) then
              begin
                value := prop.GetValue(Self).ToString;
                case prop.GetValue(Self).Kind of
                  tkString, tkChar, tkWChar, tkWString, tkUString, tkLString:
                    value := QuotedStr(value);
                  tkInteger, tkInt64, tkFloat:
                    value := value;
                else
                  value := QuotedStr(value);
                end;
                setExp := (fieldAttr as TFieldInfo).Name + '=' + value;
                setExps := setExps + ',' + setExp;
              end
              else
              begin
                whereExp := (fieldAttr as TFieldInfo).Name + '=' + prop.GetValue(Self).ToString;
              end;
          end;
        end;

        Delete(setExps, 1, 1);

        sql := Format(SQL_UPDATE_TEMPLATE, [table, setExps, whereExp]);

        TSql.ExecuteSql(sql);

        Result := sql;
      end;
    end;
  finally
    context.Free;
  end;
end;

function TModel.Update: string;
var
  context: TRttiContext;
  prop: TRttiProperty;
  typ: TRttiType;
  tableAttr, fieldAttr: TCustomAttribute;
  setExp, setExps, whereExp, value, pkValue, table, sql: string;
  params: TParams;
  param: TParam;
begin
  context := TRttiContext.Create;
  params := TParams.Create(Application);
  setExp := EmptyStr;
  setExps := EmptyStr;
  whereExp := EmptyStr;
  value := EmptyStr;
  pkValue := EmptyStr;
  table := EmptyStr;
  sql := EmptyStr;
  try
    typ := context.GetType(ClassType);
    for tableAttr in typ.GetAttributes do
    begin
      if tableAttr is TTableInfo then
      begin
        table := (tableAttr as TTableInfo).Name;
        for prop in typ.GetProperties do
        begin
          for fieldAttr in prop.GetAttributes do
          begin
            if (fieldAttr is TFieldInfo) then
              if not SameStr((fieldAttr as TFieldInfo).Name, PRIMARY_KEY_NAME) then
              begin
                setExp := (fieldAttr as TFieldInfo).Name + '=:' + (fieldAttr as TFieldInfo).Name;
                setExps := setExps + ',' + setExp;

                value := prop.GetValue(Self).ToString;
                case prop.GetValue(Self).Kind of
                  tkString, tkChar, tkWChar, tkWString, tkUString, tkLString:
                    param := params.CreateParam(ftString, ':' + (fieldAttr as TFieldInfo).Name, ptInput);
                  tkInteger, tkInt64:
                    param := params.CreateParam(ftInteger, ':' + (fieldAttr as TFieldInfo).Name, ptInput);
                  tkFloat:
                    param := params.CreateParam(ftFloat, ':' + (fieldAttr as TFieldInfo).Name, ptInput);
                else
                  param := params.CreateParam(ftString, ':' + (fieldAttr as TFieldInfo).Name, ptInput);
                end;
                param.Value := value;
              end
              else
              begin
                whereExp := (fieldAttr as TFieldInfo).Name + '=:' + (fieldAttr as TFieldInfo).Name;
                pkValue := prop.GetValue(Self).ToString;
              end;
          end;
        end;

        param := params.CreateParam(ftInteger, ':' + PRIMARY_KEY_NAME, ptInput);
        param.Value := pkValue;

        Delete(setExps, 1, 1);

        sql := Format(SQL_UPDATE_TEMPLATE, [table, setExps, whereExp]);

        TSql.ExecuteSql(sql, params);

        Result := sql;
      end;
    end;
  finally
    context.Free;
  end;
end;

end.
