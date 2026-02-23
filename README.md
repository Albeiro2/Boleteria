
***

## 🎟️ README — Sistema de Boletería

```markdown
# 🎟️ Sistema de Boletería — Java & MySQL

Sistema de escritorio para la gestión y emisión de tiquetes/boletas,
con validación de existencia en tiempo real, desarrollado en Java puro
con arquitectura MVC y principios de POO.

## 📌 Descripción

Aplicación de escritorio orientada a la administración de boletería
para eventos o servicios. Permite emitir nuevas boletas, verificar si
una boleta ya existe en el sistema y consultar el historial de emisiones,
todo con persistencia en MySQL.

## 🚀 Funcionalidades

- 🎫 **Generación de Boletas** — Emisión de boletas únicas con código identificador
- 🔎 **Verificación de Boletas** — Validación en tiempo real para evitar duplicados
- 📋 **Historial de Boletería** — Consulta y gestión de todas las boletas emitidas
- ❌ **Control de duplicados** — El sistema impide emitir una boleta ya existente
- 💾 **Persistencia MySQL** — Registro y consulta de boletas en base de datos

## 🏗️ Arquitectura

El proyecto sigue el patrón de diseño **MVC (Modelo - Vista - Controlador)**:

