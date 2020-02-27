
// Este módulo contiene el código a disposición por parte de IFDRIVERS
// en una base TAL CUAL. Todo receptor del Modulo se considera
// bajo licencia de los derechos de autor de IFDRIVERS para utilizar el
// codigo fuente siempre en modo que el o ella considere conveniente,
// incluida la copia, la compilacion, su modificacion o la redistribucion,
// con o sin modificaciones. Ninguna licencia o patentes de IFDRivers
// este implicita en la presente licencia.
//
// El usuario del codigo fuente debera entender que IFDRIVERS no puede
// Proporcionar apoyo tecnico para el modulo y no sera Responsable
// de las consecuencias del uso del programa.
//
// Todas las comunicaciones, incluida esta, no deben ser removidos
// del modulo sin el consentimiento previo por escrito de IFDRIVERS
// www: http://www.impresoras-fiscales.com.ar/
// email: soporte@impresoras-fiscales.com.ar

// Instrucciones para usar el driver y las funciones de alto nivel en Java:
//
// 1) Instale la libreria Spark32Jv.dll. Para que Java pueda encuentrar
//    la libreria, esta debe estar instalada en un directorio que este incluido en 
//    la variable de entorno PATH. 
// 2) Agregue este archivo con la clase BixolonVE a su proyecto.
// 3) Declare y cree la clase en su codigo. 
//    Todas las funciones de la clase BixolonVE seran accesibles tambien 
//    desde esta clase: IF_OPEN, IF_CLOSE,etc. mas las funciones de alto nivel.
// 
// Por ejemplo:
//
// import IFDrivers.BixolonVE;
//
// BixolonVE m_objBixolonVE = new BixolonVE();
//
// int nError = m_objBixolonVE.IF_OPEN("COM1",9600);
//
// ....etc
//
//IMPORTANTE: No debera renombrar ni el nombre del Package ni de la clase. De lo contrario,
//el driver dejara de funcionar.
//
package IFDrivers;

/**
 * 
 * @author Marcelo
 */
public class BixolonVE
{
 /**
  * Abrir el puerto de comunicaciones.
  * @param strPort - Puerto de comunicaciones.
  * @param nSpeed - Velocidad del puerto.
  * @return int - 0 si no hay error, -1 si se produjo un error
  */
  public native int IF_OPEN(String strPort, int nSpeed);

 /**
  *  Cerrar el puerto de comunicaciones.
  * @return int - 0 si no hay error, -1 si se produjo un error.
  */
  public native int IF_CLOSE();

 /**
  *  Leer un campo de la respuesta del controlador fiscal.
  * @param nField - Nro del campo de la respuesta fiscal a recuperar.
  * @return String - El valor del campo.
  */
  public native String IF_READ(int nField);

 /**
  *  Enviar un comando a la impresora fiscal.
  * @param strCommand - Comando a enviar.
  * @return int - 0 si no hay error, -1 si se produjo un error.
  */
  public native int IF_WRITE(String strCommand);

 /**
  *  Leer el código de estado del mecanismo impresor.
  * @param nBit - Nro del bit a leer (1 a 16).
  * @return int - 1 si esta en On, 0 si esta en Off
  */
  public native int IF_ERROR1(int nBit);

 /**
  *  Leer el código de estado del controlador fiscal.
  * @param nBit - Nro del bit a leer (1 a 16)
  * @return int - 1 si esta en On, 0 si esta en Off
  */
  public native int IF_ERROR2(int nBit);

 /**
  *  Habilitar o deshabilitar la depuración de comandos.
  * @param  nTrace - 1 para habilitar, 0 para deshabilitar la depuración.
  */
  public native void IF_TRACE(int nTrace);
  
  public native void IF_SERIAL(String strSerial);

  /**
   * Cargar el Spark32Jv.dll
   */
  static
  {
   System.loadLibrary("Spark32Jv");
  }

 //***************************************************************
 // 1. Comandos para apertura del sistema.
 /**
  * Abrir Impresora Fiscal.
  * @param nVar1 - Nro. de PC (siempre 01) (nn).
  * @param nVar2 - Número de secreto (ASCII) (nnnnn).
  * @return int - 0 si no hay error y != 0 si hay un error.
  */
  public int OpenPrinter(Integer nVar1, Integer nVar2)
  {
   String strBuff = "@OpenPrinter" + "|" + nVar1.toString() + "|" + nVar2.toString();
   
   int nError = IF_WRITE(strBuff);
   
   return (nError);
  }

 /**
  * Cerrar Impresora Fiscal.
  * @return int - 0 si no hay error y != 0 si hay un error.
  */
  public int ClosePrinter()
  {
   String strBuff = "@ClosePrinter";
   
   int nError = IF_WRITE(strBuff);
   
   return (nError);
  }

 /**
  * Inicio/Fin modo de Entrenamiento.
  * @param byVar1 - Modo {IF}.
  * @return int - 0 si no hay error y != 0 si hay un error.
  */
  public int TrainingMode(String byVar1)
  {
   String strBuff = "@TrainingMode" + "|" + byVar1;
   
   int nError = IF_WRITE(strBuff);
   
   return (nError);
  }

 /**
  * Logon - Cajero.
  * @param nVar1 - Número secreto(ASCII) (nnnnn).
  * @return int - 0 si no hay error y != 0 si hay un error.
  */
  public int Login(Integer nVar1)
  {
   String strBuff = "@Login" + "|" + nVar1.toString();
   
   int nError = IF_WRITE(strBuff);
   
   return (nError);
  }

 /**
  * Log-off cajero.
  * @return int - 0 si no hay error y != 0 si hay un error.
  */
  public int Logoff()
  {
   String strBuff = "@Logoff";
   
   int nError = IF_WRITE(strBuff);
   
   return (nError);
  }

 //***************************************************************
 // 2. Comandos de comprobante fiscal.
 /**
  * Datos comprador factura.
  * @param nVar1 - Nro. de línea ( 1 al 12) (nn).
  * @param strVar2 - Nombre (max 40 bytes).
  * @return int - 0 si no hay error y != 0 si hay un error.
  */
  public int SetCustomerData(Integer nVar1, String strVar2)
  {
   String strBuff = "@SetCustomerData" + "|" + nVar1.toString() + "|" + strVar2;
   
   int nError = IF_WRITE(strBuff);
   
   return (nError);
  }

 /**
  * Este comando permite ingresar el RIF o C.I. del Cliente.
  * @param strVar1 - Numero de Registro Único de Información Fiscal (RIF) o CI del comprador (max 40 bytes).
  * @return int - 0 si no hay error y != 0 si hay un error.
  */
  public int SetCustomerTIN(String strVar1)
  {
   String strBuff = "@SetCustomerTIN" + "|" + strVar1;
   
   int nError = IF_WRITE(strBuff);
   
   return (nError);
  }

 /**
  * Este comando permite ingresar datos de la Razón Social (línea 1).
  * @param strVar1 - Nombre o Razón Social del comprador (línea 1) (max 40 bytes).
  * @return int - 0 si no hay error y != 0 si hay un error.
  */
  public int SetCustomerInfo1(String strVar1)
  {
   String strBuff = "@SetCustomerInfo1" + "|" + strVar1;
   
   int nError = IF_WRITE(strBuff);
   
   return (nError);
  }

 /**
  * Este comando permite ingresar datos de la Razón Social (línea 1).
  * @param strVar1 - Nombre o Razón Social del comprador (línea 2) (max 40 bytes)
  * @return int - 0 si no hay error y != 0 si hay un error
  */
  public int SetCustomerInfo2(String strVar1)
  {
   String strBuff = "@SetCustomerInfo2" + "|" + strVar1;
   
   int nError = IF_WRITE(strBuff);
   
   return (nError);
  }

 /**
  * Este comando permite programar la Razón Social y RIF del cliente en el 
  * encabezado de una factura / nota de débito / nota de crédito.
  * @param strVar1 - Nombre o Razón Social del comprador (línea 1) (max 40 bytes).
  * @param strVar2 - Nombre o Razón Social del comprador (línea 2) (max 40 bytes).
  * @param strVar3 - Número de RIF (Registro Único de Información Fiscal) o CI del comprador (max 40 bytes).
  * @return int - 0 si no hay error y != 0 si hay un error.
  */
  public int SetCustomerExtraData(String strVar1, String strVar2, String strVar3)
  {
   String strBuff = "@SetCustomerExtraData" + "|" + strVar1 + "|" + strVar2 + "|" + 
                      strVar3;
   
   int nError = IF_WRITE(strBuff);
   
   return (nError);
  }

 /**
  * Imprimir ítem de línea en tiquet fiscal.
  * @param strVar1	Descripción del ítem (max 40 bytes).
  * @param dblVar2	Cantidad (nnnnn.nnn).
  * @param dblVar3	Monto del ítem (nnnnnnnn.nn).
  * @param nVar4	Tasa de IVA a utilizar (n).
  * @param byVar5	Calificador de línea de ítem.
  * @return int - 0 si no hay error y != 0 si hay un error.
  */
  public int PrintLineItem(String strVar1, Double dblVar2, Double dblVar3, Integer nVar4, 
                            String byVar5)
  {
   String strBuff = "@PrintLineItem" + "|" + strVar1 + "|" + dblVar2.toString() + "|" + 
                      dblVar3.toString() + "|" + nVar4.toString() + "|" + 
                       byVar5;
   
   int nError = IF_WRITE(strBuff);
   
   return (nError);
  }

 /**
  * Corrección de Error.
  * @return int - 0 si no hay error y != 0 si hay un error.
  */
  public int LastItemCancel()
  {
   String strBuff = "@LastItemCancel";
   
   int nError = IF_WRITE(strBuff);
   
   return (nError);
  }

 /**
  * Subtotal con Impresión.
  * @return int - 0 si no hay error y != 0 si hay un error.
  */
  public int Subtotal()
  {
   String strBuff = "@Subtotal";
   
   int nError = IF_WRITE(strBuff);
   
   return (nError);
  }

 /**
  * Realiza un descuento sobre el último ítem registrado o sobre el subtotal.
  * @param dblVar1 - Porcentaje / Monto directo  (nn.nn).
  * @param byVar2 - Calificador {DdPp}.
  * @return int - 0 si no hay error y != 0 si hay un error.
  */
  public int LastItemDiscount(Double dblVar1, String byVar2)
  {
   String strBuff = "@LastItemDiscount" + "|" + dblVar1.toString() + "|" + byVar2;
   
   int nError = IF_WRITE(strBuff);
   
   return (nError);
  }

 /**
  * Pago Directo.
  * @param nVar1 - Medio de pago ( 1 al 16) (nn).
  * @return int - 0 si no hay error y != 0 si hay un error.
  */
  public int DirectPayment(Integer nVar1)
  {
   String strBuff = "@DirectPayment" + "|" + nVar1.toString();
   
   int nError = IF_WRITE(strBuff);
   
   return (nError);
  }

 /**
  * Medio de Pago (Pago Parcial).
  * @param nVar1 - Medio de pago(1 al 16) (nn).
  * @param dblVar2 - Monto de pago (nnnnnnnnnn.nn).
  * @return int - 0 si no hay error y != 0 si hay un error.
  */
  public int TotalTender(Integer nVar1, Double dblVar2)
  {
   String strBuff = "@TotalTender" + "|" + nVar1.toString() + "|" + dblVar2.toString();
   
   int nError = IF_WRITE(strBuff);
   
   return (nError);
  }

 /**
  * Mensaje en Factura.
  * @param strVar1 - Texto Fiscal (max 20 bytes).
  * @return int - 0 si no hay error y != 0 si hay un error.
  */
  public int PrintFiscalText(String strVar1)
  {
   String strBuff = "@PrintFiscalText" + "|" + strVar1;
   
   int nError = IF_WRITE(strBuff);
   
   return (nError);
  }

 /**
  * Registro de Producto para devolucion.
  * @param strVar1 - Descripción del ítem (max 40 bytes).
  * @param dblVar2 - Cantidad (nnnnn.nnn).
  * @param dblVar3 - Monto del ítem (nnnnnnnn.nn).
  * @param nVar4 - Tipo de tasa de IVA a utilizar (n).
  * @return int - 0 si no hay error y != 0 si hay un error.
  */
  public int RefundItem(String strVar1, Double dblVar2, Double dblVar3, Integer nVar4)
  {
   String strBuff = "@RefundItem" + "|" + strVar1 + "|" + dblVar2.toString() + "|" + 
                      dblVar3.toString() + "|" + nVar4.toString();
   
   int nError = IF_WRITE(strBuff);
   
   return (nError);
  }

 /**
  * Cierre de devolución.
  * @param dblVar1 - Monto de pago 12 dígitos (nnnnnnnnnn.nn).
  * @param nVar2 - Medio de Pago (nn).
  * @return int - 0 si no hay error y != 0 si hay un error.
  */
  public int RefundClose(Double dblVar1, Integer nVar2)
  {
   String strBuff = "@RefundClose" + "|" + dblVar1.toString() + "|" + nVar2.toString();
   
   int nError = IF_WRITE(strBuff);
   
   return (nError);
  }

 /**
  * Cancelación.
  * @return int - 0 si no hay error y != 0 si hay un error.
  */
  public int Cancel()
  {
   String strBuff = "@Cancel";
   
   int nError = IF_WRITE(strBuff);
   
   return (nError);
  }

 /**
  * Codigo de Barras.
  * @param nVar1 - Tipo de código.
  * @param strVar2 - Datos (max 32 bytes).
  * @param byVar3 - Impresión numérica {PN}.
  * @param byVar4 - Cuando imprimir {PN}.
  * @return int - 0 si no hay error y != 0 si hay un error.
  */
  public int BarCode(Integer nVar1, String strVar2, String byVar3, String byVar4)
  {
   String strBuff = "@BarCode" + "|" + nVar1.toString() + "|" + strVar2 + "|" + 
                      byVar3 + "|" + byVar4;
   
   int nError = IF_WRITE(strBuff);
   
   return (nError);
  }

 //***************************************************************
 // 3. Comandos de comprobante no fiscal.
 /**
  * Egreso / Ingreso de Efectivo (P/O, R/A).
  * @param nVar1 - Medio de pago (nn).
  * @param dblVar2 - Monto de pago (nnnnnnnnnn.nn).
  * @param byVar3 - Calificador de la operación {01}.
  * @return int - 0 si no hay error y != 0 si hay un error.
  */
  public int PrintCashItem(Integer nVar1, Double dblVar2, String byVar3)
  {
   String strBuff = "@PrintCashItem" + "|" + nVar1.toString() + "|" + dblVar2.toString() + "|" + 
                      byVar3;
   
   int nError = IF_WRITE(strBuff);
   
   return (nError);
  }

 /**
  * Fin de Reporte de Egreso / Ingreso de Efectivo.
  * @return int - 0 si no hay error y != 0 si hay un error.
  */
  public int CloseCashReceipt()
  {
   String strBuff = "@CloseCashReceipt";
   
   int nError = IF_WRITE(strBuff);
   
   return (nError);
  }

 /**
  * Texto en documento no fiscal.
  * @param strVar1 - Texto no fiscal (max 40 bytes).
  * @param byVar2 - Modo {01}.
  * @return int - 0 si no hay error y != 0 si hay un error.
  */
  public int PrintNonFiscalText(String strVar1, String byVar2)
  {
   String strBuff = "@PrintNonFiscalText" + "|" + strVar1 + "|" + byVar2;
   
   int nError = IF_WRITE(strBuff);
   
   return (nError);
  }

 //***************************************************************
 // 4. Comando de diagnóstico y consulta.
 /**
  * Consulta de estado.
  * @param byVar1 - Código de la operación {12367N}.
  * @return int - 0 si no hay error y != 0 si hay un error.
  */
  public int StatusRequest(String byVar1)
  {
   String strBuff = "@StatusRequest" + "|" + byVar1;
   
   int nError = IF_WRITE(strBuff);
   
   return (nError);
  }

 //***************************************************************
 // 5. Comandos de Inicialización, baja y configuración
 /**
  * Programación de Tasa de Impuesto.
  * @param byVar1 - Tipo de Tasa 1 {012}.
  * @param dblVar2 - Valor de la Tasa 1 (nn.nn).
  * @param byVar3 - Tipo de Tasa 2 {012}.
  * @param dblVar4 - Valor de la Tasa 2 (nn.nn).
  * @param byVar5 - Tipo de Tasa 3 {012}.
  * @param dblVar6 - Valor de la Tasa 3 (nn.nn).
  * @return int - 0 si no hay error y != 0 si hay un error.
  */
  public int ProgramTaxes(String byVar1, Double dblVar2, String byVar3, Double dblVar4, 
                           String byVar5, Double dblVar6)
  {
   String strBuff = "@ProgramTaxes" + "|" + byVar1 + "|" + dblVar2.toString() + "|" + 
                      byVar3 + "|" + dblVar4.toString() + "|" + byVar5 + "|" + 
                       dblVar6.toString();
   
   int nError = IF_WRITE(strBuff);
   
   return (nError);
  }

 /**
  * Registrar Tasas en la Memoria Fiscal.
  * @return int - 0 si no hay error y != 0 si hay un error.
  */
  public int SaveTaxes()
  {
   String strBuff = "@SaveTaxes";
   
   int nError = IF_WRITE(strBuff);
   
   return (nError);
  }

 /**
  * Programación de Datos del Cajero.
  * @param nVar1 - Nro. de Cajero (1 al 15) (nn).
  * @param strVar2 - Password (max 5 bytes).
  * @param strVar3 - Nombre del cajero (max 16 bytes).
  * @return int - 0 si no hay error y != 0 si hay un error.
  */
  public int ProgramClerk(Integer nVar1, String strVar2, String strVar3)
  {
   String strBuff = "@ProgramClerk" + "|" + nVar1.toString() + "|" + strVar2 + "|" + 
                      strVar3;
   
   int nError = IF_WRITE(strBuff);
   
   return (nError);
  }

 /**
  * Programación de nombre de medio de pago.
  * @param nVar1 - Nro. medio de pago (1 al 16) (nn).
  * @param strVar2 - Nombre del medio de pago (max 14 bytes).
  * @return int - 0 si no hay error y != 0 si hay un error.
  */
  public int ProgramPaymentMedia(Integer nVar1, String strVar2)
  {
   String strBuff = "@ProgramPaymentMedia" + "|" + nVar1.toString() + "|" + strVar2;
   
   int nError = IF_WRITE(strBuff);
   
   return (nError);
  }

 /**
  * Programación de fecha.
  * @param strVar1 - Fecha (DDMMYY) (max 6 bytes).
  * @return int - 0 si no hay error y != 0 si hay un error.
  */
  public int SetDate(String strVar1)
  {
   String strBuff = "@SetDate" + "|" + strVar1;
   
   int nError = IF_WRITE(strBuff);
   
   return (nError);
  }

 /**
  * Programación de hora.
  * @param strVar1 - Hora (HHMMSS) (max 6 bytes)
  * @return int - 0 si no hay error y != 0 si hay un error
  */
  public int SetTime(String strVar1)
  {
   String strBuff = "@SetTime" + "|" + strVar1;
   
   int nError = IF_WRITE(strBuff);
   
   return (nError);
  }

 /**
  * Programación de Símbolos.
  * @param nVar1 - Nro. de símbolo 0 al 123  (nnn).
  * @param strVar2 - Descripción del símbolo (max 14 bytes).
  * @return int - 0 si no hay error y != 0 si hay un error.
  */
  public int ProgramSymbol(Integer nVar1, String strVar2)
  {
   String strBuff = "@ProgramSymbol" + "|" + nVar1.toString() + "|" + strVar2;
   
   int nError = IF_WRITE(strBuff);
   
   return (nError);
  }

 /**
  * Configuracion del controlador por parámetros.
  * @param nVar1 - Nro. de Parámetro (1..20) (nn).
  * @param nVar2 - Valor booleano del parámetro (0,1) (nn).
  * @return int - 0 si no hay error y != 0 si hay un error.
  */
  public int ConfigureControllerByOne(Integer nVar1, Integer nVar2)
  {
   String strBuff = "@ConfigureControllerByOne" + "|" + nVar1.toString() + "|" + 
                      nVar2.toString();
   
   int nError = IF_WRITE(strBuff);
   
   return (nError);
  }

 /**
  * Imprimir valor de programación.
  * @return int - 0 si no hay error y != 0 si hay un error.
  */
  public int PrintConfigurationData()
  {
   String strBuff = "@PrintConfigurationData";
   
   int nError = IF_WRITE(strBuff);
   
   return (nError);
  }

 //***************************************************************
 // 6. Comandos de Control Fiscal.
 /**
  * Reportes de Cierres.
  * @param byVar1 - Modo {XZ}.
  * @param byVar2 - Tipo de Cierre {01}.
  * @return int - 0 si no hay error y != 0 si hay un error.
  */
  public int DailyClose(String byVar1, String byVar2)
  {
   String strBuff = "@DailyClose" + "|" + byVar1 + "|" + byVar2;
   
   int nError = IF_WRITE(strBuff);
   
   return (nError);
  }

 /**
  * Reportes de Memoria Fiscal por Fechas.
  * @param strVar1 - Fecha 1 (DDMMYY) (max 6 bytes).
  * @param strVar2 - Fecha 2 (DDMMYY) (max 6 bytes).
  * @param byVar3 - Imprimir {IU}.
  * @param byVar4 - Modo {ASM}.
  * @return int - 0 si no hay error y != 0 si hay un error.
  */
  public int DailyCloseByDate(String strVar1, String strVar2, String byVar3, String byVar4)
  {
   String strBuff = "@DailyCloseByDate" + "|" + strVar1 + "|" + strVar2 + "|" + 
                      byVar3 + "|" + byVar4;
   
   int nError = IF_WRITE(strBuff);
   
   return (nError);
  }

 /**
  * Reportes de Auditoria por número de Z.
  * @param nVar1 - Nro. de Z inicial del período (nnnn).
  * @param nVar2 - Nro. de Z final del período (nnnn).
  * @param byVar3 - Imprimir {IU}.
  * @param byVar4 - Modo {ASM}.
  * @return int - 0 si no hay error y != 0 si hay un error.
  */
  public int DailyCloseByNumber(Integer nVar1, Integer nVar2, String byVar3, String byVar4)
  {
   String strBuff = "@DailyCloseByNumber" + "|" + nVar1.toString() + "|" + nVar2.toString() + "|" + 
                      byVar3 + "|" + byVar4;
   
   int nError = IF_WRITE(strBuff);
   
   return (nError);
  }

 /**
  * Reimprimir el último documento almacenado en la memoria de auditoria.
  * @return int - 0 si no hay error y != 0 si hay un error.
  */
  public int Reprint()
  {
   String strBuff = "@Reprint";
   
   int nError = IF_WRITE(strBuff);
   
   return (nError);
  }

 /**
  * Re-impresión de documentos almacenados en memoria de auditoria por rango de fechas.
  * @param strVar1 - Fecha inicial del per�odo (YYMMDD) (max 6 bytes).
  * @param strVar2 - Fecha final del per�odo (YYMMDD) (max 6 bytes).
  * @param byVar3 - Modo {FCNZR}.
  * @return int - 0 si no hay error y != 0 si hay un error.
  */
  public int ReprintByDate(String strVar1, String strVar2, String byVar3)
  {
   String strBuff = "@ReprintByDate" + "|" + strVar1 + "|" + strVar2 + "|" + byVar3;
   
   int nError = IF_WRITE(strBuff);
   
   return (nError);
  }

 /**
  * Re-impresión de documentos almacenados en memoria de auditoria por rango de número.
  * @param nVar1 - Nro. de documento inicial del período (nnnnnnn).
  * @param nVar2 - Nro. de documento final del período (nnnnnnn).
  * @param byVar3 - Modo {FCNZR}.
  * @return int - 0 si no hay error y != 0 si hay un error.
  */
  public int ReprintByNumber(Integer nVar1, Integer nVar2, String byVar3)
  {
   String strBuff = "@ReprintByNumber" + "|" + nVar1.toString() + "|" + nVar2.toString() + "|" + 
                      byVar3;
   
   int nError = IF_WRITE(strBuff);
   
   return (nError);
  }

 /**
  * Re-impresión de documentos almacenados en memoria de auditoria por nro. de Cédula o RIF.
  * @param strVar1 - Nro. de RIF o CI (max 9 bytes).
  * @param byVar2 - Modo {JGVE}.
  * @return int - 0 si no hay error y != 0 si hay un error.
  */
  public int ReprintByTIN(String strVar1, String byVar2)
  {
   String strBuff = "@ReprintByTIN" + "|" + strVar1 + "|" + byVar2;
   
   int nError = IF_WRITE(strBuff);
   
   return (nError);
  }

 /**
  * Imprimir Estado de la Memoria de Auditoría.
  * @return int - 0 si no hay error y != 0 si hay un error.
  */
  public int PrintAuditStatusReport()
  {
   String strBuff = "@PrintAuditStatusReport";
   
   int nError = IF_WRITE(strBuff);
   
   return (nError);
  }

 //***************************************************************
 // 7. Comandos Generales.
 /**
  * Apertura de Gaveta de Dinero.
  * @return int - 0 si no hay error y != 0 si hay un error.
  */
  public int OpenDrawer()
  {
   String strBuff = "@OpenDrawer";
   
   int nError = IF_WRITE(strBuff);
   
   return (nError);
  }

 /**
  * Limpiar el buffer de impresion
  * @return int - 0 si no hay error y != 0 si hay un error.
  */
  public int ResetPrinterBuffer()
  {
   String strBuff = "@ResetPrinterBuffer";
   
   int nError = IF_WRITE(strBuff);
   
   return (nError);
  }

 /**
  * Imprimir página de prueba.
  * @return int - 0 si no hay error y != 0 si hay un error.
  */
  public int PrintTest()
  {
   String strBuff = "@PrintTest";
   
   int nError = IF_WRITE(strBuff);
   
   return (nError);
  }

 /**
  * Programación de encabezado y pie de página.
  * @param nVar1 - Nro. de Línea (nn).
  * @param strVar2 - Texto (max 40 bytes).
  * @return int - 0 si no hay error y != 0 si hay un error.
  */
  public int SetHeaderTrailer(Integer nVar1, String strVar2)
  {
   String strBuff = "@SetHeaderTrailer" + "|" + nVar1.toString() + "|" + strVar2;
   
   int nError = IF_WRITE(strBuff);
   
   return (nError);
  }

 /**
  * Enviar un comando en el formato nativo de la impresora fiscal.
  * @param strVar1 - Una cadena de caracteres conteniendo el comando y 
  * los parámetros en el formato nativo (la trama de datos).
  * @return int - 0 si no hay error y != 0 si hay un error
  */
  public int SendRawCommand(String strVar1)
  {
   String strBuff = "@SendRawCommand" + "|" + strVar1;
   
   int nError = IF_WRITE(strBuff);
   
   return (nError);
  }

 //***************************************************************
 // 8. Comandos de Display
 /**
  * Muestra en el display la hora y la fecha.
  * @return int - 0 si no hay error y != 0 si hay un error
  */
  public int DisplayDateTime()
  {
   String strBuff = "@DisplayDateTime";
   
   int nError = IF_WRITE(strBuff);
   
   return (nError);
  }

 /**
  * Muestra en la parte superior del display el mensaje comercial.
  * @return int - 0 si no hay error y != 0 si hay un error.
  */
  public int DisplayCommercial()
  {
   String strBuff = "@DisplayCommercial";
   
   int nError = IF_WRITE(strBuff);
   
   return (nError);
  }

 /**
  * Muestra un mensaje en el display.
  * @param byVar1 - Nro. de linea {UL}.
  * @param strVar2 - Mensaje a exhibir (max 20 bytes).
  * @return int - 0 si no hay error y != 0 si hay un error.
  */
  public int DisplayMessage(String byVar1, String strVar2)
  {
   String strBuff = "@DisplayMessage" + "|" + byVar1 + "|" + strVar2;
   
   int nError = IF_WRITE(strBuff);
   
   return (nError);
  }

 /**
  * Programación de Mensaje Comercial.
  * @param strVar1 - Mensaje 50 Caracteres (max 50 bytes).
  * @return int - 0 si no hay error y != 0 si hay un error.
  */
  public int ProgramCommercial(String strVar1)
  {
   String strBuff = "@ProgramCommercial" + "|" + strVar1;
   
   int nError = IF_WRITE(strBuff);
   
   return (nError);
  }

 /**
  * Programación de Mensajes.
  * @param nVar1 - Nro. de mensaje 0 al 59  (nn).
  * @param strVar2 - Descripci�n del mensaje (max 20 bytes).
  * @return int - 0 si no hay error y != 0 si hay un error.
  */
  public int ProgramMessage(Integer nVar1, String strVar2)
  {
   String strBuff = "@ProgramMessage" + "|" + nVar1.toString() + "|" + strVar2;
   
   int nError = IF_WRITE(strBuff);
   
   return (nError);
  }

 //***************************************************************
 // 9. Comandos relacionados a los cheques.
 /**
  * Modo de Slip.
  * @param byVar1 - Modo {01}.
  * @return int - 0 si no hay error y != 0 si hay un error.
  */
  public int ModeSlip(String byVar1)
  {
   String strBuff = "@ModeSlip" + "|" + byVar1;
   
   int nError = IF_WRITE(strBuff);
   
   return (nError);
  }

 /**
  * Formato del Cheque (Parte Frontal).
  * @param nVar1 - Número de Lineas entre el Top y la Cantidad (n).
  * @param nVar2 - Número de Líneas entre la Cantidad y No Endosable (n).
  * @param nVar3 - Número de Líneas entre No endosable y el Nombre (n).
  * @param nVar4 - Número de Líneas entre el Nombre y la Cantidad (n).
  * @param nVar5 - Número de Líneas entre la Cantidad y la Fecha) (n).
  * @param dblVar6 - Monto (nnnnnnnn.nn).
  * @param strVar7 - Datos del cliente (max 50 bytes).
  * @param strVar8 - Datos de la fecha (max 50 bytes).
  * @return int - 0 si no hay error y != 0 si hay un error.
  */
  public int FormatCheck(Integer nVar1, Integer nVar2, Integer nVar3, Integer nVar4, 
                          Integer nVar5, Double dblVar6, String strVar7, String strVar8)
  {
   String strBuff = "@FormatCheck" + "|" + nVar1.toString() + "|" + nVar2.toString() + "|" + 
                      nVar3.toString() + "|" + nVar4.toString() + "|" + 
                       nVar5.toString() + "|" + dblVar6.toString() + "|" + 
                        strVar7 + "|" + strVar8;
   
   int nError = IF_WRITE(strBuff);
   
   return (nError);
  }

 /**
  * Formato del Cheque (Endoso).
  * @param nVar1 - Numero de L�nea a ser enviada por cada comando (n).
  * @param strVar2 - Texto variable (max 30 bytes).
  * @return int - 0 si no hay error y != 0 si hay un error.
  */
  public int FormatEndorse(Integer nVar1, String strVar2)
  {
   String strBuff = "@FormatEndorse" + "|" + nVar1.toString() + "|" + strVar2;
   
   int nError = IF_WRITE(strBuff);
   
   return (nError);
  }

 /**
  * Imprimir Endoso.
  * @param nVar1 - Numero de Líneas entre el Top y N°: (1 a 9) (n)
  * @return int - 0 si no hay error y != 0 si hay un error.
  */
  public int PrintEndorse(Integer nVar1)
  {
   String strBuff = "@PrintEndorse" + "|" + nVar1.toString();
   
   int nError = IF_WRITE(strBuff);
   
   return (nError);
  }

 /**
  * Modo de Validación.
  * @param byVar1 - Modo {01}.
  * @return int - 0 si no hay error y != 0 si hay un error.
  */
  public int ModeValidation(String byVar1)
  {
   String strBuff = "@ModeValidation" + "|" + byVar1;
   
   int nError = IF_WRITE(strBuff);
   
   return (nError);
  }

 /**
  * Impresión de Validación.
  * @param strVar1 - Texto a Imprimir (max 50 bytes).
  * @return int - 0 si no hay error y != 0 si hay un error.
  */
  public int PrintValidation(String strVar1)
  {
   String strBuff = "@PrintValidation" + "|" + strVar1;
   
   int nError = IF_WRITE(strBuff);
   
   return (nError);
  }

 /**
  * Lectura del MICR.
  * @return int - 0 si no hay error y != 0 si hay un error.
  */
  public int ReadMICR()
  {
   String strBuff = "@ReadMICR";
   
   int nError = IF_WRITE(strBuff);
   
   return (nError);
  }

}
