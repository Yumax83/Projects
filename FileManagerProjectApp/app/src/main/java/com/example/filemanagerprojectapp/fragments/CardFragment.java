package com.example.filemanagerprojectapp.fragments;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.filemanagerprojectapp.FileAdapter;
import com.example.filemanagerprojectapp.FileOpener;
import com.example.filemanagerprojectapp.OnFileSelectedListener;
import com.example.filemanagerprojectapp.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CardFragment extends Fragment implements OnFileSelectedListener {
    View view;
    File storage;
    private FileAdapter fileAdapter;
    private RecyclerView recyclerView;

    String data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_card, container, false);

        ImageView imgBackCard = view.findViewById(R.id.img_back_card);
        TextView tvPathHolderCard = view.findViewById(R.id.tv_path_holder_card);

        File[] externalDirs = ContextCompat.getExternalFilesDirs(getContext(), null);
        // Метод getExternalFilesDirs() Получает массив путей, где приложение может хранить файлы:
        // externalDirs[0] — это внутренняя память (например:
        // externalDirs[1] — это SD-карта, если она вставлена

        if (externalDirs.length > 1 && externalDirs[1] != null) {
            // Проверка: В массиве есть хотя бы 2 пути (length > 1) и Второй путь (SD-карта) реально существует (не null).
            // если проще то строка читается так "Если в массиве есть второй путь, и он реально указывает на SD-карту — работаем с ним!"           // Внешняя SD-карта доступна. Действие:           storage = new File(externalDirs[1].getAbsolutePath()); // Запоминаешь путь к папке на SD-карте           cardTvPathFolder.setText("SD-карта: " + storage.getAbsolutePath());// Показываешь путь на экране           runtimePermission(); // вызов метода запроса разрешений       } else {           // Если SD-карты нет — используем внутреннюю           storage = new File(Environment.getExternalStorageDirectory().getAbsolutePath()); //Используем стандартный путь к внутренней памяти           cardTvPathFolder.setText("Встроенная память: " + storage.getAbsolutePath()); //Отображаем, что работаем с внутренней           runtimePermission(); // вызов метода запроса разрешений

            // получаем доступ к внешней карте памяти
            storage = new File(externalDirs[1].getAbsolutePath()); // Запоминаешь путь к папке на SD-карте
         //  tvPathHolderCard.setText("Внешняя SD-карта (список файлов и папок):");// Показываешь путь на экране
           tvPathHolderCard.setText("SD-карта: " + storage.getAbsolutePath());// Показываешь путь на экране
            // вызов метода запроса разрешений
            runtimePermission();
        } else {
            // Если SD-карты нет — используем внутреннюю
            storage = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
            //Используем стандартный путь к внутренней памяти
            tvPathHolderCard.setText("Встроенная память: " + storage.getAbsolutePath());
            //Отображаем, что работаем с внутренней
            runtimePermission(); // вызов метода запроса разрешений
        }

        return view;
    }

    private void runtimePermission() {
        // Разрешение до Android 10 (API 29)
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.Q) {
            if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
            }
            if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                displayFiles();
            }
        }
        // Разрешения для Android 11 (API 30) и выше
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (!Environment.isExternalStorageManager()) {
                try {
                    Uri uri = Uri.fromParts("package", getActivity().getPackageName(), null);
                    Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                    intent.setData(uri);
                    intent.addCategory("android.intent.category.DEFAULT");
                    intent.setData(Uri.parse(String.format("package:%s", getActivity().getPackageName())));
                    getActivity().startActivityIfNeeded(intent, 101);
                } catch (Exception e) {
                    Intent intent = new Intent();
                    intent.setAction(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                    getActivity().startActivityIfNeeded(intent, 101);
                }
            }
            if (Environment.isExternalStorageManager()) {
                displayFiles();
            }
        }
    }

    public ArrayList<File> findFiles(File file) {
        ArrayList<File> arrayList = new ArrayList<>();
        File[] files = file.listFiles();

        for (File singleFile : files) {
            if (singleFile.isDirectory() && !singleFile.isHidden()) {
                arrayList.add(singleFile);
            }
        }

        for (File singleFile : files) {
            if (singleFile.getName().toLowerCase().endsWith(".jpeg") ||
                    singleFile.getName().toLowerCase().endsWith(".jpg") ||
                    singleFile.getName().toLowerCase().endsWith(".png") ||
                    singleFile.getName().toLowerCase().endsWith(".mp3") ||
                    singleFile.getName().toLowerCase().endsWith(".wav") ||
                    singleFile.getName().toLowerCase().endsWith(".mp4") ||
                    singleFile.getName().toLowerCase().endsWith(".pdf") ||
                    singleFile.getName().toLowerCase().endsWith(".doc") ||
                    singleFile.getName().toLowerCase().endsWith(".apk")) {
                arrayList.add(singleFile);
            }
        }
        return arrayList;
    }

    private void displayFiles() {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_card);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        List<File> fileList = new ArrayList<>();
        fileList.addAll(findFiles(storage));

        fileAdapter = new FileAdapter(getContext(), fileList, this);
        recyclerView.setAdapter(fileAdapter);
    }

    @Override
    public void onFileClicked(File file) {
        if(file.isDirectory()){
//            Bundle bundle = new Bundle();
//            bundle.putString("path", storage.getAbsolutePath());
          // InternalFragment internalFragment = new InternalFragment();
            //internalFragment.setArguments(bundle);
          CardFragment cardFragment = new CardFragment();
//          cardFragment.setArguments(storage.getAbsolutePath());

         // requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, internalFragment).addToBackStack(null).commit();
        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, cardFragment).addToBackStack(null).commit();

        }
    }


    @Override
    public void onFileLongClicked(File file) {

    }
}