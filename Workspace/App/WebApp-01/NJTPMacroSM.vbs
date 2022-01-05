Set objExcel = CreateObject("Excel.Application")
Set objWorkbook = objExcel.Workbooks.Open("D:\\TSG\Workspace\App\WebApp-01\NJTPViolMacro.xlsm")

objExcel.Application.Run "NJTPViolMacro.xlsm!PopulateData" 
objExcel.Application.Run "NJTPViolMacro.xlsm!ImportData" 
objWorkbook.Save
objExcel.ActiveWorkbook.Close

objExcel.Application.Quit
WScript.Quit