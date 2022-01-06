<?php

    $file_db = "db_wisata.db";

    try{
        $pdo = new PDO("sqlite:$file_db");
        $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
        $pdo->setAttribute(PDO::ATTR_EMULATE_PREPARES, false);

        $sql_create = "CREATE TABLE IF NOT EXISTS 'wisata'(
            'id' integer NOT NULL PRIMARY KEY AUTOINCREMENT,
            'nama_wisata' text NOT NULL,
            'deskripsi' text NOT NULL,
            'alamat' text NOT NULL,
            'harga_tiket' text NOT NULL,
            'gambar' text NOT NULL,
            'latitut' float NOT NULL,
            'longitut' float NOT NULL,
            'created_at' datetime NOT NULL DEFAULT CURRENT_TIMESTAMP)";
        
        $pdo->exec($sql_create);

    }catch(PDOException $e){
        throw new PDOException($e->getMessage(), (int)$e->getCode());

    }

    header('Content-Type: application/json');

    if($_SERVER['REQUEST_METHOD'] == 'GET'){
        $query = 'SELECT * FROM wisata order by created_at desc';
        $stmt = $pdo->prepare($query);
        $stmt->execute();
        $data = $stmt->fetchAll(PDO::FETCH_ASSOC);
        echo json_encode($data);

    }elseif($_SERVER['REQUEST_METHOD'] == 'POST'){
        $nama_wisata = $_POST['nama_wisata'];
        $deskripsi = $_POST['deskripsi'];
        $alamat = $_POST['alamat'];
        $harga_tiket = $_POST['harga_tiket'];
        $gambar = $_POST['gambar'];
        $latitut = $_POST['latitut'];
        $longitut = $_POST['longitut'];

        $query = 'INSERT INTO wisata (nama_wisata, deskripsi, alamat, harga_tiket, gambar, latitut, longitut) VALUES (?,?,?,?,?,?,?)';
        $stmt = $pdo->prepare($query);
        $res = $stmt->execute([$nama_wisata,$deskripsi,$alamat,$harga_tiket,$gambar,$latitut,$longitut]);

        if($res){
            $data = ['nama_wisata' => $nama_wisata, 'deskripsi'=>$deskripsi, 'alamat'=>$alamat, 'harga_tiket'=>$harga_tiket, 'gambar'=>$gambar, 'latitut'=>$latitut, 'longitut'=>$longitut];
            echo json_encode($data);
        }else{
            echo json_encode(['error'=>$stmt->errorCode()]);
        }

    }elseif($_SERVER['REQUEST_METHOD'] == 'DELETE'){
        $id = $_GET['id'];
        $query = 'DELETE FROM wisata WHERE id = ?';
        $stmt = $pdo->prepare($query);
        $res = $stmt->execute([$id]);

        if($res){
            $data = ['id'=>$id];
            echo json_encode($data);
        }else{
            echo json_encode(['error'=>$stmt->errorCode()]);
        }
        
    }