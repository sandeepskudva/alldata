Set objExcel = CreateObject("Excel.Application")
Set objWorkbook = objExcel.Workbooks.Open("D:\\TSG\Workspace\App\WebApp-01\GSPViolMacro.xlsm")

objExcel.Application.Run "GSPViolMacro.xlsm!PopulateData" 
objExcel.Application.Run "GSPViolMacro.xlsm!ImportData" 
objWorkbook.Save
objExcel.ActiveWorkbook.Close

objExcel.Application.Quit
WScript.Quit